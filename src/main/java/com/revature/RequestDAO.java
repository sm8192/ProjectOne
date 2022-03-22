package com.revature;

import java.util.List;

public interface RequestDAO {

    List listRequests();
    List listMyResolvedRequests(String username);
    List listMyPendingRequests(String username);
    List listAllPendingRequests();
    List listAllResolvedRequests();
    void newRequest(Request request);
    void resolveRequest(Request request);
    Request getRequest(int id);
}
