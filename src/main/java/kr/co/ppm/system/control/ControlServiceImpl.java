package kr.co.ppm.system.control;


import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
import kr.co.ppm.system.parasolstatus.ParasolStatus;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Service
public class ControlServiceImpl implements ControlService{
    @Autowired
    private ParasolMapper parasolMapper;
    private Logger logger = LogManager.getLogger(ControlServiceImpl.class);
    private Map<String, String> actionList = new Hashtable<String, String>();

    @Override
    public String sendControl(Parasol parasol, String action) {
        Parasol controlParasol = parasolMapper.selectById(parasol);

        actionList.put(controlParasol.getId(), action);

        String url = "http://" + "172.16.31.108:8080" + "/device/" + action;

        logger.info("this is url : " + url);

        String responseCode = null;

        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try(Response response = client.newCall(request).execute()) {
                responseCode = response.body().string();
            }

            if ("200".equals(responseCode.split(":")[1].split("\"")[1])) {
                logger.info("Send Control is Success");
            } else {
                logger.error(responseCode.split(":")[2].split("\"")[1]);
            }

            logger.info(responseCode);
        } catch (Exception e) {
            logger.info("Exception Occurred in method sendControl");
        }

        return responseCode;
    }

    @Override
    public String analysisStatus(ParasolStatus parasolStatus) {
        String receiveId = parasolStatus.getParasolId();

        if (actionList.containsKey(receiveId)) {
            String sendAction = actionList.get(receiveId);

            if (!(sendAction.equals(parasolStatus.getStatus()))) {
                return sendAction;
            }
        }

        return null;
    }
}
