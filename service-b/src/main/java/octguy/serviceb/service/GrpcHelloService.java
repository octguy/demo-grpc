package octguy.serviceb.service;

import io.grpc.stub.StreamObserver;
import octguy.grpc.HelloRequest;
import octguy.grpc.HelloResponse;
import octguy.grpc.HelloServiceGrpc;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcHelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sendMessage(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Received from service B: " + request.getMessage())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
