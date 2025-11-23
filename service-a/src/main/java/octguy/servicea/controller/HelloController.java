package octguy.servicea.controller;

import octguy.servicea.service.GrpcHelloClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final GrpcHelloClientService grpcHelloClientService;

    public HelloController(GrpcHelloClientService grpcHelloClientService) {
        this.grpcHelloClientService = grpcHelloClientService;
    }

    @GetMapping("/hello")
    public String hello(@RequestBody String message) {
        return grpcHelloClientService.callServiceB(message);
    }
}
