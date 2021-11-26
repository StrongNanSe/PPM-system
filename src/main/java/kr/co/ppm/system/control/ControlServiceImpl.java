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
import java.util.List;

@Service
public class ControlServiceImpl implements ControlService{
    @Autowired
    private ParasolMapper parasolMapper;
    private Logger logger = LogManager.getLogger(ControlServiceImpl.class);

    @Override
    public void sendControl(Parasol parasol, String action) {
        String agentIpAddress = parasolMapper.selectById(parasol).getAgentIpAddress();
        String url = "http://" + "localhost:8080" + "/device/" + action;

        logger.info("this is url : " + url);

        try{
            String response = sendGetType(url);
            logger.info(response);
        } catch (Exception e) {
            //오류 처리 필요
            logger.info(e.toString());
        }
    }

    @Override
    public List<String> analysisStatus(ParasolStatus parasolStatus) {
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
