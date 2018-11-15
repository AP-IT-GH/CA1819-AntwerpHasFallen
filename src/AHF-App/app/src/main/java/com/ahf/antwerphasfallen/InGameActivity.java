package com.ahf.antwerphasfallen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InGameActivity extends AppCompatActivity {


    public static final GameDataService service = RetrofitInstance.getRetrofitInstance().create(GameDataService.class);

    public Player CurrentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        int gameId = 0;
        int playerId = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            gameId = extras.getInt("gameId");
            playerId = extras.getInt("playerId");
            loadPlayer(playerId);
        }

        TextView txtGameId = (TextView)findViewById(R.id.txt_gameId);
        txtGameId.setText("Game id: " + gameId + "\nPlayer id: " + playerId);

        Button btn_endGame = findViewById(R.id.btn_endGame);
        btn_endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmEndGameDialog dialog = new ConfirmEndGameDialog();
                dialog.show(getSupportFragmentManager(), "confirm end game");
            }
        });
    }

    private void loadPlayer(int id) {
        Call<Player> call = service.getPlayer(id);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                CurrentPlayer = response.body();
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }

    public void EndGame(){
        Call<Boolean> call = service.endGame(CurrentPlayer.getGameId());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body() != null) {
                    Toast.makeText(InGameActivity.this, "Game ended", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InGameActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(InGameActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
