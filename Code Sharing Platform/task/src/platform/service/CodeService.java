package platform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.data.entity.Code;
import platform.data.remote.entity.RemoteCode;
import platform.data.remote.entity.RemoteLatestCode;
import platform.data.repository.CodeRepository;
import platform.data.remote.request.CodeRequest;
import platform.data.remote.response.CodeResponse;
import platform.data.remote.response.EmptyJsonResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(
        CodeRepository codeRepository
    ) {
        this.codeRepository = codeRepository;
    }

    public List<RemoteCode> getCodeLatest() {
        List<Code> codes = codeRepository.findAvailable();
        List<RemoteCode> remoteCodes = new ArrayList<>();
        for (Code code : codes) {
            remoteCodes.add(new RemoteCode(
                code.getDateFormatted(),
                code.getCode(),
                code.getFormattedTime(),
                code.getFormattedViews(),
                false, false
            ));
        }

        return remoteCodes;
    }

    public RemoteCode getCodeByToken(String token) {

        Optional<Code> optionalCode = codeRepository.findOptionalByToken(token);
        if (optionalCode.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Not found"
            );
        }

        Code code = optionalCode.get();

        Duration duration = Duration.between(code.getDate(), LocalDateTime.now());

        if (code.getViews() != -1) {
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
        }

        Long timeCount = code.getTime() - duration.toSeconds();
        timeCount = timeCount <= 0 ? 0 : timeCount;

        return new RemoteCode(
            code.getDateFormatted(),
            code.getCode(),
            timeCount,
            code.getFormattedViews(),
            code.getTime() != -1,
            code.getViews() != -1
        );
    }

    public CodeResponse setCode(CodeRequest request) {
        Code code = new Code();
        code.setDate(LocalDateTime.now());
        code.setCode(request.getCode());
        code.setToken(UUID.randomUUID().toString());
        code.setViews(request.getViews() <= 0 ? -1 : request.getViews());
        code.setTime(request.getTime() <= 0 ? -1 : request.getTime());
        Code savedCode = codeRepository.save(code);

        return new CodeResponse(savedCode.getToken());
    }
}
