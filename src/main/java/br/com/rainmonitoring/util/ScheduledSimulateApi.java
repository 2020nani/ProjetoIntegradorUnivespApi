package br.com.rainmonitoring.util;

import br.com.rainmonitoring.areasrisco.AreaRisco;
import br.com.rainmonitoring.areasrisco.AreaRiscoController;
import br.com.rainmonitoring.areasrisco.AreaRiscoRepository;
import br.com.rainmonitoring.notificacaorisco.NotificacaoRiscoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ScheduledSimulateApi {

    private AreaRiscoRepository areaRiscoRepository;

    @Autowired
    SimpMessagingTemplate template;

    public ScheduledSimulateApi(AreaRiscoRepository areaRiscoRepository) {
        this.areaRiscoRepository = areaRiscoRepository;
    }


    @Scheduled(cron = "0 0 0/10 * * *")
    @Transactional
    public String executar() {
        List<AreaRisco> areaRiscoList = areaRiscoRepository.findAll();
        if (!areaRiscoList.isEmpty()) {
            areaRiscoList.forEach(areaRisco -> {
                if (areaRisco.getIndicePluvial() < 90) {
                    areaRisco.setIndicePluvial(areaRisco.getIndicePluvial() + 10);
                } else {
                    areaRisco.setIndicePluvial(20);
                }
                AreaRisco areaRiscoUpdate = new AreaRisco(
                        areaRisco.getNome(),
                        areaRisco.getLatitude(),
                        areaRisco.getLongitude(),
                        areaRisco.getIndicePluvial(),
                        areaRisco.getStatusRisco()
                );
                areaRiscoUpdate.setId(areaRisco.getId());
                areaRiscoRepository.save(areaRiscoUpdate);
            });

        }else{
            System.out.println("Nao ha area de risco salva");
        }
        List<AreaRisco> areaRiscoListUpdate = areaRiscoRepository.findAll();
        template.convertAndSend("/topic/area",areaRiscoListUpdate);
        return "ok";
    }

    @SendTo("/topic/area")
    public List<AreaRisco> send() throws Exception {
        List<AreaRisco> areaRiscoList = areaRiscoRepository.findAll();
        return areaRiscoList;
    }
}
