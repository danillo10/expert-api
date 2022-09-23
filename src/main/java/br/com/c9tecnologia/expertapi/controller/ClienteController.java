package br.com.c9tecnologia.expertapi.controller;

import br.com.c9tecnologia.expertapi.exception.ResourceNotFoundException;
import br.com.c9tecnologia.expertapi.model.Cliente;
import br.com.c9tecnologia.expertapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    public Cliente create(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente clienteDetalhes) {
        Cliente cliente = clienteRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        clienteDetalhes.setId(id);
        clienteRepository.save(clienteDetalhes);
        return ResponseEntity.ok(clienteDetalhes);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> destroy(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
