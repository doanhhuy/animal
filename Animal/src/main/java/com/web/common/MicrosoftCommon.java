package com.web.common;

import com.web.bean.ErrorException;
import com.web.bean.MicrosoftJson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 3/9/17.
 */
public class MicrosoftCommon {

    public Object[] getTypeColorFromImage(File destinationFile) {
        TranslateCommon translateCommon = new TranslateCommon();
        DateCommon dateCommon = new DateCommon();
        CompareJSONCommon compareJSONCommon = new CompareJSONCommon();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> color = new ArrayList<String>();
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/vision/v1.0/analyze");

            builder.setParameter("visualFeatures", "Tags");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "382f5abd65f74494935027f65a41a4bc");

            FileEntity reqEntity = new FileEntity(destinationFile, "application/octet-stream");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(json);
                JSONArray tags = (JSONArray) jsonObject.get("tags");
                List<MicrosoftJson> microsoftJsons = new ArrayList<MicrosoftJson>();
                String name = null;
                String hint = null;
                for (int i = 0; i < tags.size(); i++) {
                    JSONObject object = (JSONObject) tags.get(i);
                    name = (String) object.get("name");
                    if (compareJSONCommon.checkType(name)) {
                        type.add(name);
                    } else {
                        String nameTemp = translateCommon.translate(name);
                        if (compareJSONCommon.checkTypeVietnamese(nameTemp)) {
                            type.add(name);
                        } else {
                            if (compareJSONCommon.checkColor(name)) {
                                color.add(name);
                            } else {
                                if (compareJSONCommon.checkColorVietnamese(nameTemp)) {
                                    color.add(name);
                                }
                            }
                        }
                    }
                    hint = (String) object.get("hint");
                    if (hint != null) {
                        if (compareJSONCommon.checkType(hint)) {
                            type.add(hint);
                        } else {
                            String hintTemp = translateCommon.translate(hint);
                            if (compareJSONCommon.checkTypeVietnamese(hintTemp)) {
                                type.add(hint);
                            } else {
                                if (compareJSONCommon.checkColor(hint)) {
                                    color.add(name);
                                } else {
                                    if (compareJSONCommon.checkColorVietnamese(hintTemp)) {
                                        color.add(name);
                                    }
                                }
                            }
                        }
                    }
                }


            } else {
                throw new ErrorException("Error Result!");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ErrorException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new Object[]{type, color};
    }

}
