package vl.example.accountsexaminer.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vl.example.accountsexaminer.external.ExternalRestServiceRestClient;
import vl.example.accountsexaminer.queue.AccountsQueueProcessor;
import vl.example.accountsexaminer.queue.CoinsQueueProcessor;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataProcessingJob {

    private final ExternalRestServiceRestClient externalRestServiceRestClient;

    private final CoinsQueueProcessor coinsQueueProcessor;
    private final AccountsQueueProcessor accountsQueueProcessor;


    @Value("${services.examiner.threshold:}")
    private Integer threshold;

    @Scheduled(fixedDelayString = "${services.examiner.fixed-delay:}", initialDelayString = "${services.examiner.initial-delay:}", timeUnit = TimeUnit.SECONDS)
    public void processExternalData() {

        coinsQueueProcessor.requestCoinsDataMessage();
    }

    @Scheduled(fixedDelayString = "${services.examiner.fixed-delay:}", initialDelayString = "${services.examiner.initial-delay:}", timeUnit = TimeUnit.SECONDS)
    public void processMail() {

        accountsQueueProcessor.requestAccountsDataMessage();
    }
}
