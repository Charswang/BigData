package case4_WebLogKpi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KPI {
    private Boolean is_validate; //验证数据是否合法
    private String remote_ip; //ip
    private String remote_user; //用户
    private String request_time; //请求时间
    private String request_method; //请求方式
    private String request_url; //请求资源路径
    private String request_http; // http协议方式
    private String request_status; //请求状态
    private String sent_bytes; //发送的字节数

    /**
     * 183.62.140.242 - - [30/May/2013:17:38:33 +0800] "GET /static/image/common/fj_btn.png HTTP/1.1" 200 217
     * @param line
     * @return
     */
    public static KPI parser(String line){
        KPI kpi = new KPI();
        String[] infos = line.split(" ");
        if (infos.length > 9){
            kpi.setRemote_ip(infos[0]);
            kpi.setRemote_user(infos[1]);
            kpi.setRequest_time(infos[3].substring(1));
            kpi.setRequest_method(infos[5].substring(1));
            kpi.setRequest_url(infos[6]);
            kpi.setRequest_http(infos[7].substring(0,infos[7].length()-1));
            kpi.setRequest_status(infos[8]);
            kpi.setSent_bytes(infos[9]);

            //过滤掉响应码 > 400的请求
            if (!"".equals(kpi.getRequest_status()) && Integer.parseInt(kpi.getRequest_status()) < 400){
                kpi.setIs_validate(true);
            }else {
                kpi.setIs_validate(false);
            }
        }else {
            kpi.setIs_validate(false);
        }
        return kpi;
    }

    public static KPI filterPv(String line){
        KPI kpi = KPI.parser(line);
        /*Set<String> set = new HashSet<String>();
        set.add("/static/image/common");
        set.add("/data/cache");
        set.add("/uc_server/data");
        set.add("/static/image/smiley");*/

        //过滤出/static/image/common路径下的请求,并统计各种请求次数
        if (kpi.getRequest_url().contains("/static/image/common")){
            kpi.setIs_validate(true);
        }else{
            kpi.setIs_validate(false);
        }
        return kpi;
    }

    @Override
    public String toString() {
        //String不可变，StringBuffer可变，线程安全，StringBuilder可变，无线程保护
        //运行速度String < StringBuffer < StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("is_validate=").append(is_validate);
        sb.append("\nremote_ip=").append(remote_ip);
        sb.append("\nremote_user=").append(remote_user);
        sb.append("\nrequest_time=").append(request_time);
        sb.append("\nrequest_method=").append(request_method);
        sb.append("\nrequest_url=").append(request_url);
        sb.append("\nrequest_http=").append(request_http);
        sb.append("\nrequest_status=").append(request_status);
        sb.append("\nsent_bytes=").append(sent_bytes);
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "183.62.140.242 - - [30/May/2013:17:38:33 +0800] \"GET /static/image/common/fj_btn.png HTTP/1.1\" 200 217";
        /*String[] s = str.split(" ");
        System.out.println(s.length);
        for (String k : s){
            System.out.println(k);
        }*/
        KPI kpi = KPI.parser(str);
        System.out.println(kpi.toString());
    }
}
