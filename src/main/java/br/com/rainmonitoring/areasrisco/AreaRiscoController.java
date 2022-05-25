package br.com.rainmonitoring.areasrisco;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AreaRiscoController {

    private AreaRiscoRepository areaRiscoRepository;

    public AreaRiscoController(AreaRiscoRepository areaRiscoRepository) {
        this.areaRiscoRepository = areaRiscoRepository;
    }

    @PostMapping("arearisco")
    public ResponseEntity<AreaRisco> criarAreaRisco(@RequestBody @Valid AreaRiscoForm areaRiscoForm){
        AreaRisco areaRisco = areaRiscoForm.converte();
        areaRiscoRepository.save(areaRisco);
        return ResponseEntity.ok(areaRisco);
    }

    @PutMapping("arearisco/{areaId}")
    public ResponseEntity<AreaRisco> editarAreaRisco(@RequestBody @Valid AreaRiscoEditForm areaRiscoEditForm,
                                                     @PathVariable("areaId") Long areaId){
        AreaRisco areaRisco = areaRiscoEditForm.update(areaId, areaRiscoRepository);
        areaRisco.setId(areaId);
        areaRiscoRepository.save(areaRisco);
        return ResponseEntity.ok(areaRisco);
    }

    @GetMapping("arearisco/{areaId}")
    public ResponseEntity<AreaRisco> findByIdAreaRisco(@PathVariable("areaId") Long areaId){
        AreaRisco areaRisco = areaRiscoRepository.findById(areaId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha area de risco cadastrado id: " + areaId));
        return ResponseEntity.ok(areaRisco);
    }

    @GetMapping("arearisco")
    public ResponseEntity<List<AreaRisco>> findAllAreaRisco(){
        List<AreaRisco> listAreaRisco = areaRiscoRepository.findAll();
        return ResponseEntity.ok(listAreaRisco);
    }


}
