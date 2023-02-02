package com.codezf;

import com.codezf.routers.HomeRouter;
import io.vertx.core.*;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;


/**
 * @author : Coder
 * @date : 2022-9-7
 * @desc :
 */
@Slf4j
public class MainServer extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(), handler -> handler.result()
                .deployVerticle(MainServer.class.getName(), new DeploymentOptions().setInstances(4)));
    }

    private Router routers() {
        Router rootRouter = Router.router(vertx);
        handleRouter(rootRouter);
        return rootRouter;
    }

    private void handleRouter(Router rootRouter) {
        HomeRouter.create(vertx).homeRouter(rootRouter);
    }

    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createHttpServer()
                .requestHandler(routers())
                .listen(9999)
                .onSuccess(success -> log.info("Http Server started on port " + success.actualPort() + " successfully "));
    }
}
