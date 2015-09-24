package oleg.osipenko.mai.data.api;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import oleg.osipenko.mai.data.dataModel.ListContent;
import oleg.osipenko.mai.data.dataModel.StaticContent;
import oleg.osipenko.mai.data.repository.specification.StaticContentSpecification;
import oleg.osipenko.mai.presentation.MainActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by olegosipenko on 24.09.15.
 */
public class NetworkProvider {

    private RestAdapter adapter;
    MaiService service;

    public NetworkProvider() {
        Gson gson = new Gson();
        adapter = new RestAdapter.Builder()
                .setEndpoint("http://mai.ru/")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        service = adapter.create(MaiService.class);
    }

    public Observable<List<ListContent>> getNews() {
        service.getNewsList(1, 30, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                try {
                    InputStream in = response.getBody().in();
                    int n = in.available();
                    byte[] bytes = new byte[n];
                    in.read(bytes, 0, n);
                    String s = new String(bytes, StandardCharsets.UTF_8);
                    int endPos = s.length() - 1;
                    String json = s.substring(1, endPos);
                    String trimmedJson = json.split("count")[0];
                    json = trimmedJson.substring(0, trimmedJson.length() - 2) + "}";
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new StringReader(json));
                    reader.setLenient(true);
                    Type itemsMapType = new TypeToken<Map<Integer, NewsHeadersResponse.Content>>() {}.getType();
                    Map<Integer, NewsHeadersResponse.Content> headers = gson.fromJson(reader, itemsMapType);
                    Log.d("aaa", json.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        return Observable.empty();
    }

    public Observable<List<StaticContent>> getNewsById(StaticContentSpecification specification) {
        String id = specification.getItem();
        service.getNewsById(id, new Callback<Response>() {
            @Override
            public void success(Response response1, Response response) {
                try {
                    InputStream in = response1.getBody().in();
                    int n = in.available();
                    byte[] bytes = new byte[n];
                    in.read(bytes, 0, n);
                    String s = new String(bytes, StandardCharsets.UTF_8);
                    int endPos = s.length() - 1;
                    String json = s.substring(1, endPos);
                    Gson gson = new Gson();
                    SingleNews singleNews = gson.fromJson(json, SingleNews.class);
                    Log.d("aaa", singleNews.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("aaa", error.getMessage());
            }
        });
        return Observable.empty();
    }
}
