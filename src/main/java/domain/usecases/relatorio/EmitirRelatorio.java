package domain.usecases.relatorio;

import java.util.List;

public interface EmitirRelatorio <T>{

    String listToString(List<T> type);

    void gerarRelatorio(List<T> type);
}
