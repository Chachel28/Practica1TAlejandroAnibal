package net.juanxxiii.practica1t.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    @SerializedName(value = "@graph")
    @Expose
    public List<Graph> graph;
}
