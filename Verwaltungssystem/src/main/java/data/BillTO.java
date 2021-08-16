package data;

import java.time.LocalDateTime;

public class  BillTO implements java.io.Serializable {

    public final LocalDateTime BillTimestart;
    public final LocalDateTime BillTimestop;
    public final OrderTO[] Orders;


    public BillTO(LocalDateTime billTimestart, LocalDateTime billTimestop, OrderTO[] orders) {
        BillTimestart = billTimestart;
        BillTimestop = billTimestop;
        Orders = orders;
    }
}
