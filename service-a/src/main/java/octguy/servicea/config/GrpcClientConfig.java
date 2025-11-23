package octguy.servicea.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import octguy.grpc.HelloServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder
                .forAddress("service-b", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public HelloServiceGrpc.HelloServiceBlockingStub helloStub(ManagedChannel channel) {
        return HelloServiceGrpc.newBlockingStub(channel);
    }
}
