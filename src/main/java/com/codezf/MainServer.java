package com.codezf;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author : Coder
 * @date : 2022-9-7
 * @desc :
 */
@Slf4j
public class MainServer extends AbstractVerticle {



    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(MainServer.class.getName());
    }


    private Router routers() {
        Router rootRouter = Router.router(vertx);
        handleHomeRouter(rootRouter);
        return rootRouter;
    }

    private void handleHomeRouter(Router rootRouter) {
        Router subRouter = Router.router(vertx);
        rootRouter.route("/home/*").handler(this::handleHome).subRouter(subRouter);
        subRouter.route("/index").handler(this::handleHomeIndex);
    }

    private void handleHome(RoutingContext request) {
        handleHomeIndex(request);
    }

    private void handleHomeIndex(RoutingContext request) {
        request.json(JSON.ok());
    }

    @Override
    public void start(Promise<Void> startPromise) {
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(routers()).listen(9999).onSuccess(success ->
                log.info("Http Server started on port " + httpServer.actualPort() + " successfully ")
        );
    }

    @Data
    static class JSON<T> {
        private Integer code;
        private String msg;
        private T data;

        private JSON() {
        }

//        private JSON(Integer code, String msg, T data) {
//            this.code = code;
//            this.msg = msg;
//            this.data = data;
//        }

        public static <T> JSON<T> ok() {
            JSON<T> json = new JSON<>();
            json.setCode(200);
            json.setMsg("success");
            return json;
        }
    }

}
