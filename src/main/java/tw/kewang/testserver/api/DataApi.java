package tw.kewang.testserver.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("data")
public class DataApi {
    static volatile ArrayList users = new ArrayList();
    static String ret;

    @Produces("application/json")
    @POST
    public Response post(String body) {
        users.add(body);
        return Response.ok().entity("OK").build();
    }

    @Path("{name}")
    @GET
    public Response get(@Context HttpHeaders headers, @PathParam("name") String ind) {
        int res;
        for (int h = 0; h < users.size(); h++) {
            String gett = (String) users.get(h);
            System.out.println("gett:" + gett);
            System.out.println("ind" + ind);
            res = gett.indexOf(ind);
            System.out.println(res);
            if (res >= 0) {
                ret = (String) users.get(h);
                break;
            } else {
                ret = "查無此人";
            }
        }
        return Response.ok().entity(ret).build();
    }

    @Path("{da}")
    @DELETE
    public Response del(@Context HttpHeaders headers, @PathParam("da") String ind) {
        int res;
        for (int h = 0; h < users.size(); h++) {
            String gett = (String) users.get(h);
            System.out.println("gett:" + gett);
            System.out.println("ind" + ind);
            res = gett.indexOf(ind);
            System.out.println(res);
            if (res >= 0) {
                users.remove(h);
                ret="已成功移除資料。";
                System.out.println(users);
                break;
            } else {
                ret = "查無此人";
            }
        }
        return Response.ok().entity(ret).build();

    }

    @Path("{dat}")
    @PUT
    public Response PUT(@Context HttpHeaders headers, @PathParam("dat") String ind,String body) {
        int res;
        for (int h = 0; h < users.size(); h++) {
            String gett = (String) users.get(h);
            System.out.println("gett:" + gett);
            System.out.println("ind" + ind);
            res = gett.indexOf(ind);
            System.out.println(res);
            if (res >= 0) {
                users.set(h,body);
                ret="已成功更新資料。" + users.get(h);
                System.out.println(users);
                break;
            } else {
                ret = "查無此人";
            }
        }
        return Response.ok().entity(ret).build();

    }
}


