package com.codezf.routers;

import com.codezf.vo.JSON;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * @author Coder
 * @date 2023/2/2-13:15
 * @description
 */
public class HomeRouter {

    private HomeRouter() {
    }

    private Vertx vertx;

    private void setVertx(Vertx vertx){
        this.vertx = vertx;
    }

    public static HomeRouter create(Vertx vertx) {
        HomeRouter homeRouter = new HomeRouter();
        homeRouter.setVertx(vertx);
        return homeRouter;
    }

    public void homeRouter(Router parentRouter) {
        Router subRouter = Router.router(vertx);
        subRouter.get("/info").handler(this::handleHomeInfo);
        subRouter.get("/index").handler(this::handleHomeIndex);
        parentRouter.route("/home/*").subRouter(subRouter);
    }

    private void handleHomeIndex(RoutingContext request) {
        request.json(JSON.ok("index"));
    }

    private void handleHomeInfo(RoutingContext request) {
        request.json(JSON.ok("info"));
    }

}
