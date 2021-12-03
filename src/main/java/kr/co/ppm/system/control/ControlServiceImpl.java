package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
import kr.co.ppm.system.parasolstatus.ParasolStatus;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
public class ControlServiceImpl implements ControlService{
    @Autowired
    private ParasolMapper parasolMapper;
    private Logger logger = LogManager.getLogger(ControlServiceImpl.class);
    private Map<String, String> actionList = new Hashtable<String, String>();

    @Override
    public void sendControl(Parasol parasol, String action) {
        Parasol controlParasol = parasolMapper.selectById(parasol);

        actionList.put(controlParasol.getId(), action);

        String url = "http://" + "172.16.31.108:8080" + "/device/" + action;

        logger.info("this is url : " + url);

        try{
            String response = sendGetType(url);
            logger.info(response);
        } catch (Exception e) {
            // TODO 오류처리
            logger.info(e.toString());
        }
    }

    @Override
    public String analysisStatus(ParasolStatus parasolStatus) {
        String receiveId = parasolStatus.getParasolId();
        String sendAction = actionList.get(receiveId);

        if (actionList.containsKey(receiveId)) {
            if (!(sendAction.equals(parasolStatus.getStatus()))) {
                return sendAction;
            }
        }

        return null;
    }

    private String sendGetType(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        //RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postBody);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
