package com.example.searchgitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchgitapp.Adapters.CodeAdapter;
import com.example.searchgitapp.Adapters.CommitAdapter;
import com.example.searchgitapp.Adapters.IssueAdapter;
import com.example.searchgitapp.Adapters.LabelAdapter;
import com.example.searchgitapp.Adapters.RespositoAdapter;
import com.example.searchgitapp.Adapters.TopicAdapter;
import com.example.searchgitapp.Adapters.UserAdapter;
import com.example.searchgitapp.Interface.ApiClient;
import com.example.searchgitapp.Interface.OpenRespEndPint;
import com.example.searchgitapp.Model.GitHubRep;
import com.example.searchgitapp.Model.GitHubSearch;
import com.example.searchgitapp.Model.GithubCode;
import com.example.searchgitapp.Model.GithubCommit;
import com.example.searchgitapp.Model.GithubIssues;
import com.example.searchgitapp.Model.GithubLabel;
import com.example.searchgitapp.Model.GithubRespo;
import com.example.searchgitapp.Model.GithubTopic;
import com.example.searchgitapp.Model.GithubUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String searchPhrase;
    RespositoAdapter adapter;
    TextView tvResults;

   GithubRespo gitHubRepNList;
   GithubTopic gitHubTopList;
   GithubUser gitHubUserList;
   GithubLabel githubLabelList;
   GithubIssues githubIssueList;
   GithubCommit githubCommit;
   GithubCode githubCode;

   LabelAdapter labelAdapter;
   IssueAdapter issueAdapter;
   CodeAdapter codeAdapter;

   Spinner spFilter;
    ArrayAdapter searchfilterAdapter;
    TopicAdapter topicAdapter;
    UserAdapter userAdapter;
    CommitAdapter commitAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvResults = (TextView)findViewById(R.id.tvResults);
        spFilter =  (Spinner)findViewById(R.id.spinner);
        searchfilterAdapter = ArrayAdapter.createFromResource(SearchActivity.this, R.array.search_filter,android.R.layout.simple_spinner_item);
        searchfilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         searchPhrase = getIntent().getStringExtra("searchPhrase");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        spFilter.setAdapter(searchfilterAdapter);
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (searchPhrase!=null){
                    if (position == 0){
                        loadSearchRepos(searchPhrase);
                    }
                    else if (position == 1)
                    {
                        loadSearchTopics(searchPhrase);
                    }
                    else if (position == 2)
                    {
                        loadSearchLabels(searchPhrase);
                    }  else if (position ==3)
                    {
                        loadGitUser(searchPhrase);
                    }
                    else if (position == 4)
                    {
                        loadIssues(searchPhrase);
                    }
                    else if (position == 5)

                    {
                        loadCommits(searchPhrase);
                    } else if (position == 6)
                    {
                        loadCode(searchPhrase);
                    }




                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void loadCode(String searchPhrase) {

        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubCode> githubcall =  apiservice.getCode(searchPhrase);
        githubcall.enqueue(new Callback<GithubCode>() {
            @Override
            public void onResponse(Call<GithubCode> call, Response<GithubCode> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    githubCode = response.body();

                    codeAdapter = new CodeAdapter(githubCode, SearchActivity.this, R.layout.user_item);
                    recyclerView.setAdapter(codeAdapter);
                    codeAdapter.notifyDataSetChanged();

                } else {

                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);

                }

            }
            @Override
            public void onFailure(Call<GithubCode> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });
    }

    private void loadCommits(String searchPhrase) {
        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubCommit> githubcall =  apiservice.getCommits(searchPhrase);
        githubcall.enqueue(new Callback<GithubCommit>() {
            @Override
            public void onResponse(Call<GithubCommit> call, Response<GithubCommit> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    githubCommit = response.body();

                    commitAdapter = new CommitAdapter(githubCommit, SearchActivity.this, R.layout.user_item);
                    recyclerView.setAdapter(commitAdapter);
                    commitAdapter.notifyDataSetChanged();

                } else {

                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }
            @Override
            public void onFailure(Call<GithubCommit> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });

    }

    private void loadIssues(String searchPhrase) {

        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubIssues> githubcall =  apiservice.getIssues(searchPhrase);
        githubcall.enqueue(new Callback<GithubIssues>() {
            @Override
            public void onResponse(Call<GithubIssues> call, Response<GithubIssues> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    githubIssueList = response.body();

                    issueAdapter = new IssueAdapter(githubIssueList, SearchActivity.this, R.layout.search_item);
                    recyclerView.setAdapter(issueAdapter);
                    issueAdapter.notifyDataSetChanged();






                } else {


                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }

            @Override
            public void onFailure(Call<GithubIssues> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });


    }

    private void loadGitUser(String searchPhrase) {
        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubUser> githubcall =  apiservice.getUser(searchPhrase);
        githubcall.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    gitHubUserList = response.body();

                    userAdapter = new UserAdapter(gitHubUserList, SearchActivity.this, R.layout.user_item);
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();


                } else {


                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });

    }

    private void loadSearchLabels(String searchPhrase) {

        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubLabel> githubcall =  apiservice.getLabels(searchPhrase);
        githubcall.enqueue(new Callback<GithubLabel>() {
            @Override
            public void onResponse(Call<GithubLabel> call, Response<GithubLabel> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    githubLabelList = response.body();

                    labelAdapter = new LabelAdapter(githubLabelList, SearchActivity.this, R.layout.search_item);
                    recyclerView.setAdapter(labelAdapter);
                    labelAdapter.notifyDataSetChanged();






                } else {


                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }

            @Override
            public void onFailure(Call<GithubLabel> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });

    }

    private void loadSearchTopics(String searchPhrase) {

        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubTopic> githubcall =  apiservice.getTopics(searchPhrase);
        githubcall.enqueue(new Callback<GithubTopic>() {
            @Override
            public void onResponse(Call<GithubTopic> call, Response<GithubTopic> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                    gitHubTopList = response.body();

                    topicAdapter = new TopicAdapter(gitHubTopList, SearchActivity.this, R.layout.search_item);
                    recyclerView.setAdapter(topicAdapter);
                    topicAdapter.notifyDataSetChanged();






                } else {


                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }

            @Override
            public void onFailure(Call<GithubTopic> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });

    }

    private void loadSearchRepos(String searchPhrase) {

        OpenRespEndPint apiservice = ApiClient.getClient().create(OpenRespEndPint.class);

        Call<GithubRespo> githubcall =  apiservice.getOpenRepositories(searchPhrase);
        githubcall.enqueue(new Callback<GithubRespo>() {
            @Override
            public void onResponse(Call<GithubRespo> call, Response<GithubRespo> response) {
                if (response.isSuccessful() && response.body()!=null){

                    tvResults.setText(getResources().getString(R.string.searchresults) + searchPhrase);

                   gitHubRepNList = response.body();

                    adapter = new RespositoAdapter(gitHubRepNList, SearchActivity.this, R.layout.search_item);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();






                } else {


                    tvResults.setText(getResources().getString(R.string.no_results) + searchPhrase);
                    recyclerView.setAdapter(null);
                }

            }

            @Override
            public void onFailure(Call<GithubRespo> call, Throwable t) {

                Log.e("FAILURE",t.getMessage());


            }
        });





    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(SearchActivity.this,MainActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}