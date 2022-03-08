package com.taewoo.project.service;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

public interface LottoService {

    public ArrayList<ArrayList<String>> getLottoHistory();
}
