package com.github.fedorchuck.app_update;

import java.io.IOException;
import java.util.List;

public interface IDestroy {

    void killByListId(List<Integer> pid) throws IOException;

    List<Integer> getListProcessIdentifier(String processNameToKill) throws IOException;


}
