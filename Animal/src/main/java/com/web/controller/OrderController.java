package com.web.controller;

import com.web.bean.ErrorException;
import com.web.bean.ErrorResponse;
import com.web.common.DateCommon;
import com.web.entity.JSON.OrderJSON;
import com.web.entity.api.OrderAPI;
import com.web.entity.backend.Members;
import com.web.entity.backend.Orders;
import com.web.service.IAccountService;
import com.web.service.IClassService;
import com.web.service.IMemberService;
import com.web.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyle on 17/02/2017.
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IMemberService memberService;

    /*
       API get all of phylum (bộ)
   */
    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public ResponseEntity<OrderJSON> getAllOrders() {
        List<Orders> ordersList = orderService.getAllOrders();
        if (ordersList.isEmpty()) {
            return new ResponseEntity<OrderJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<OrderAPI> orderAPIS = new ArrayList<OrderAPI>();
            OrderAPI orderAPI = null;
            Members members;
            for (Orders p : ordersList) {
                orderAPI = new OrderAPI();
                orderAPI.setId(p.getId());
                orderAPI.setNotation(p.getNotation());
                orderAPI.setIdClass(p.getIdClass().getId());
                orderAPI.setScienceNameClass(p.getIdClass().getScienceName());
                orderAPI.setVietnameseNameClass(p.getIdClass().getVietnameseName());
                orderAPI.setScienceName(p.getScienceName());
                orderAPI.setVietnameseName(p.getVietnameseName());
                orderAPI.setDiscovererName(p.getDiscovererName());
                orderAPI.setYearDiscover(p.getYearDiscover());
                orderAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                orderAPI.setNameCreator(members.getFullName());
                orderAPI.setDateUpdate(p.getDateUpdate().toString());
                orderAPI.setDateCreate(p.getDateCreate().toString());
                orderAPI.setStatus(p.getStatus());
                orderAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                orderAPI.setNameChecker(members.getFullName());
                orderAPIS.add(orderAPI);
            }
            OrderJSON orderJSON = new OrderJSON();
            orderJSON.setList(orderAPIS);
            return new ResponseEntity<OrderJSON>(orderJSON, HttpStatus.OK);
        }
    }

    /*
       API get phylum by Id (bộ)
   */
    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderJSON> getOrderById(@PathVariable("id") int id) {
        Orders orders = orderService.getOrderById(id);
        if (orders == null) {
            return new ResponseEntity<OrderJSON>(HttpStatus.NO_CONTENT);
        } else {
            Members members;
            OrderAPI orderAPI = new OrderAPI();
            orderAPI.setId(orders.getId());
            orderAPI.setNotation(orders.getNotation());
            orderAPI.setIdClass(orders.getIdClass().getId());
            orderAPI.setScienceNameClass(orders.getIdClass().getScienceName());
            orderAPI.setVietnameseNameClass(orders.getIdClass().getVietnameseName());
            orderAPI.setScienceName(orders.getScienceName());
            orderAPI.setVietnameseName(orders.getVietnameseName());
            orderAPI.setDiscovererName(orders.getDiscovererName());
            orderAPI.setYearDiscover(orders.getYearDiscover());
            orderAPI.setIdCreator(orders.getIdCreator().getId());
            members = memberService.getMemberById(orders.getIdCreator().getId());
            orderAPI.setNameCreator(members.getFullName());
            orderAPI.setDateUpdate(orders.getDateUpdate().toString());
            orderAPI.setDateCreate(orders.getDateCreate().toString());
            orderAPI.setStatus(orders.getStatus());
            orderAPI.setIdChecker(orders.getIdChecker().getId());
            members = memberService.getMemberById(orders.getIdChecker().getId());
            orderAPI.setNameChecker(members.getFullName());
            OrderJSON orderJSON = new OrderJSON();
            orderJSON.setOrder(orderAPI);
            return new ResponseEntity<OrderJSON>(orderJSON, HttpStatus.OK);
        }
    }

    /*
       API create a new phylum (bộ)
       Have check isExist
   */
    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderJSON> createPhylum(@RequestBody OrderAPI orderAPI) throws ErrorException {
        DateCommon dateCommon = new DateCommon();
        Orders orders = new Orders();
        orders.setNotation(orderAPI.getNotation());
        orders.setIdClass(classService.getClassById(orderAPI.getIdClass()));
        orders.setScienceName(orderAPI.getScienceName());
        orders.setVietnameseName(orderAPI.getVietnameseName());
        orders.setYearDiscover(orderAPI.getYearDiscover());
        orders.setDiscovererName(orderAPI.getDiscovererName());
        orders.setStatus(orderAPI.getStatus());
        orders.setDateCreate(dateCommon.getDateNow());
        orders.setDateUpdate(dateCommon.getTimestampNow());
        if (orderService.isOrderExistByOrder(orders)) {
            throw new ErrorException("bộ này đã tồn tại!");
        } else {
            orders.setIdCreator(accountService.getAccountById(orderAPI.getIdCreator()));
            orders.setIdChecker(accountService.getAccountById(orderAPI.getIdChecker()));
            int id = orderService.addOrder(orders);
            orders = orderService.getOrderById(id);
            Members members;
            orderAPI.setId(orders.getId());
            orderAPI.setNotation(orders.getNotation());
            orderAPI.setIdClass(orders.getIdClass().getId());
            orderAPI.setScienceNameClass(orders.getIdClass().getScienceName());
            orderAPI.setVietnameseNameClass(orders.getIdClass().getVietnameseName());
            orderAPI.setScienceName(orders.getScienceName());
            orderAPI.setVietnameseName(orders.getVietnameseName());
            orderAPI.setDiscovererName(orders.getDiscovererName());
            orderAPI.setYearDiscover(orders.getYearDiscover());
            orderAPI.setIdCreator(orders.getIdCreator().getId());
            members = memberService.getMemberById(orders.getIdCreator().getId());
            orderAPI.setNameCreator(members.getFullName());
            orderAPI.setDateUpdate(orders.getDateUpdate().toString());
            orderAPI.setDateCreate(orders.getDateCreate().toString());
            orderAPI.setStatus(orders.getStatus());
            orderAPI.setIdChecker(orders.getIdChecker().getId());
            members = memberService.getMemberById(orders.getIdChecker().getId());
            orderAPI.setNameChecker(members.getFullName());
            OrderJSON orderJSON = new OrderJSON();
            orderJSON.setOrder(orderAPI);
            return new ResponseEntity<OrderJSON>(orderJSON, HttpStatus.CREATED);
        }
    }

    /*
       API  update  phylum (bộ)
   */
    @RequestMapping(value = "/api/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderJSON> updateOrder(@PathVariable("id") int id, @RequestBody OrderAPI orderAPI) throws ErrorException {
        Orders orderById = orderService.getOrderById(id);
        if (orderById == null) {
            throw new ErrorException(" ID bộ không tồn tại!");
        } else if (orderService.isOrderExistByOrder(orderById)) {
            throw new ErrorException("bộ này đã tồn tại!");
        } else {
            DateCommon dateCommon = new DateCommon();
            Orders orders = new Orders();
            orders.setId(id);
            orders.setNotation(orderAPI.getNotation());
            orders.setIdClass(classService.getClassById(orderAPI.getIdClass()));
            orders.setScienceName(orderAPI.getScienceName());
            orders.setVietnameseName(orderAPI.getVietnameseName());
            orders.setYearDiscover(orderAPI.getYearDiscover());
            orders.setDiscovererName(orderAPI.getDiscovererName());
            orders.setStatus(orderAPI.getStatus());
            orders.setDateUpdate(dateCommon.getTimestampNow());
            orders.setIdChecker(accountService.getAccountById(orderAPI.getIdChecker()));
            orders.setIdCreator(accountService.getAccountById(orderAPI.getIdCreator()));
            orderService.updateOrder(orders);
            orders = orderService.getOrderById(id);
            Members members;
            orderAPI.setId(orders.getId());
            orderAPI.setNotation(orders.getNotation());
            orderAPI.setIdClass(orders.getIdClass().getId());
            orderAPI.setScienceNameClass(orders.getIdClass().getScienceName());
            orderAPI.setVietnameseNameClass(orders.getIdClass().getVietnameseName());
            orderAPI.setScienceName(orders.getScienceName());
            orderAPI.setVietnameseName(orders.getVietnameseName());
            orderAPI.setDiscovererName(orders.getDiscovererName());
            orderAPI.setYearDiscover(orders.getYearDiscover());
            orderAPI.setIdCreator(orders.getIdCreator().getId());
            members = memberService.getMemberById(orders.getIdCreator().getId());
            orderAPI.setNameCreator(members.getFullName());
            orderAPI.setDateUpdate(orders.getDateUpdate().toString());
            orderAPI.setDateCreate(orders.getDateCreate().toString());
            orderAPI.setStatus(orders.getStatus());
            orderAPI.setIdChecker(orders.getIdChecker().getId());
            members = memberService.getMemberById(orders.getIdChecker().getId());
            orderAPI.setNameChecker(members.getFullName());
            OrderJSON orderJSON = new OrderJSON();
            orderJSON.setOrder(orderAPI);
            return new ResponseEntity<OrderJSON>(orderJSON, HttpStatus.CREATED);
        }

    }

    @RequestMapping(value = "/api/order/class/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderJSON> getOrderByClassId(@PathVariable("id") int id) {
        List<Orders> ordersList = orderService.getOrderByClassId(id);
        if (ordersList.isEmpty()) {
            return new ResponseEntity<OrderJSON>(HttpStatus.NO_CONTENT);
        } else {
            List<OrderAPI> orderAPIS = new ArrayList<OrderAPI>();
            OrderAPI orderAPI = null;
            Members members;
            for (Orders p : ordersList) {
                orderAPI = new OrderAPI();
                orderAPI.setId(p.getId());
                orderAPI.setNotation(p.getNotation());
                orderAPI.setIdClass(p.getIdClass().getId());
                orderAPI.setScienceNameClass(p.getIdClass().getScienceName());
                orderAPI.setVietnameseNameClass(p.getIdClass().getVietnameseName());
                orderAPI.setScienceName(p.getScienceName());
                orderAPI.setVietnameseName(p.getVietnameseName());
                orderAPI.setDiscovererName(p.getDiscovererName());
                orderAPI.setYearDiscover(p.getYearDiscover());
                orderAPI.setIdCreator(p.getIdCreator().getId());
                members = memberService.getMemberById(p.getIdCreator().getId());
                orderAPI.setNameCreator(members.getFullName());
                orderAPI.setDateUpdate(p.getDateUpdate().toString());
                orderAPI.setDateCreate(p.getDateCreate().toString());
                orderAPI.setStatus(p.getStatus());
                orderAPI.setIdChecker(p.getIdChecker().getId());
                members = memberService.getMemberById(p.getIdChecker().getId());
                orderAPI.setNameChecker(members.getFullName());
                orderAPIS.add(orderAPI);
            }
            OrderJSON orderJSON = new OrderJSON();
            orderJSON.setList(orderAPIS);
            return new ResponseEntity<OrderJSON>(orderJSON, HttpStatus.OK);
        }
    }

    /*
       Function return error message by JSON
   */
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }


}
