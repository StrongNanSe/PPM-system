package kr.co.ppm.system.control;


import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
import kr.co.ppm.system.parasolstatus.ParasolStatus;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

        String url = "http://" + controlParasol.getAgentIpAddress() + "/device/" + action;
        String responseCode = null;

        logger.info("----------INFO----------");
        logger.info("| Send This URL : " + url + " |");
        logger.info("------------------------");

        try{
            OkHttpClient client = new OkHttpClient()
                    .newBuilder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try(Response response = client.newCall(request).execute()) {
                responseCode = response.body() != null
                        ? response.body().string()
                        : null;
            }

            if ("200".equals(responseCode.split(":")[1].split("\"")[1])) {
                logger.info("----------INFO----------");
                logger.info("| Send Control is Success |");
                logger.info("-------------------------");
            } else {
                logger.error("**********ERROR**********");
                logger.error("* " + responseCode.split(":")[2].split("\"")[1] + " *");
                logger.error("*************************");
            }

            logger.info("----------INFO----------");
            logger.info("| " + responseCode + " |");
            logger.info("------------------------");
        } catch (Exception e) {
            logger.info("----------INFO----------");
            logger.info("| Exception Occurred in method sendControl |");
            logger.info("------------------------");
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
