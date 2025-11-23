package octguy.servicea.service;

import octguy.grpc.HelloRequest;
import octguy.grpc.HelloResponse;
import octguy.grpc.HelloServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class GrpcHelloClientService {

    private final HelloServiceGrpc.HelloServiceBlockingStub helloStub;

    public GrpcHelloClientService(HelloServiceGrpc.HelloServiceBlockingStub helloStub) {
        this.helloStub = helloStub;
    }

    public String callServiceB(String message) {
        HelloRequest request = HelloRequest.newBuilder()
                .setMessage(message + " is sent from service A")
                .build();

        HelloResponse response = helloStub.sendMessage(request);
        return response.getMessage();
    }
}
