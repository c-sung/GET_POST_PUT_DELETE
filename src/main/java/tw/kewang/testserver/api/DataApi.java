package tw.kewang.testserver.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("data")
public class DataApi {
    static volatile ArrayList<String> users = new ArrayList();

    @Produces("application/json")
    @POST
    public Response post(String body) {
        users.add(body);
        return Response.ok().entity("OK").build();
    }

    static int num = 0;

    @Path("{keyword_GET}")
    @GET
    public Response get(@Context HttpHeaders headers, @PathParam("keyword_GET") String kw) {
        boolean checkThis = false;
        checkThis = detect(kw, checkThis);
        if (checkThis) {
            return Response.ok().entity(users.get(num)).build();
        } else {
            return Response.ok().entity("查無此人").build();

        }
    }


    @Path("{keyword_DEL}")
    @DELETE
    public Response del(@Context HttpHeaders headers, @PathParam("keyword_DEL") String kw) {
        boolean checkThis = false;
        checkThis = detect(kw, checkThis);
        if (checkThis) {
            users.remove(num);
            System.out.println(users);
            return Response.ok().entity("已成功移除資料。").build();
        } else {
            return Response.ok().entity("查無此人。").build();
        }
    }


    @Path("{keyword_PUT}")
    @PUT
    public Response PUT(@Context HttpHeaders headers, @PathParam("keyword_PUT") String kw, String body) {
        boolean checkThis = false;
        checkThis = detect(kw, checkThis);
        if (checkThis) {
            users.set(num, body);
            System.out.println(users);
            return Response.ok().entity("更新成功! \n" + users.get(num)).build();
        } else {
            return Response.ok().entity("查無此人").build();
        }
    }


    static boolean detect(String key, boolean check1) {
        for (num = 0; num < users.size(); num++) {
            String gett = users.get(num);
            System.out.println("gett:" + gett);
            System.out.println("kw" + key);
            if (gett.indexOf(key) >= 0) {
                check1 = true;
                return check1;
            }
        }

        return check1;
    }
}





