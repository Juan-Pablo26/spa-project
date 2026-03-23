package cl.spa.backend.controller;

import cl.spa.backend.model.Plan;
import cl.spa.backend.repository.PlanRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Duoc
 */
@RestController
@RequestMapping("/api/planes")
@CrossOrigin("*")
public class PlanController {
    
    private final PlanRepository repository;

    public PlanController(PlanRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public List<Plan> getPlanes(){
        return repository.findAll();
    }
    // buscar a los planes por el id por el cual tenga este
    @GetMapping("/{id}")
    public Plan getPlanById(@PathVariable Long id){
        return repository.getReferenceById(id);
    }
    // agregar un nuevo plan
    @PutMapping
    public Plan newPlan(@RequestBody Plan plan){
        return repository.save(plan);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        repository.deleteById(id);
    }

}
