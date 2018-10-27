package com.ahf.antwerphasfallen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jorren on 26/10/2018.
 */

public class CreateTeamsDialog extends DialogFragment {
    private static final String TAG = "CreateTeamsDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_teamnames, null);

        int teamCount = 2;
        if(getArguments() != null)
            if(getArguments().getInt("teams") != 0)
                teamCount = getArguments().getInt("teams");

        ArrayList<Team> teamList = new ArrayList<>();
        for(int i = 0; i < teamCount; i++)
            teamList.add(new Team("Team " + (i + 1)));
        final Game game = new Game();
        game.setTeams(teamList);
        TeamNamesListAdapter adapter = new TeamNamesListAdapter(getActivity(), teamList);
        ListView lvTeams = (ListView)dialogView.findViewById(R.id.list_teamnames);
        lvTeams.setAdapter(adapter);

        builder.setView(dialogView)
                .setTitle("Choose teamnames")
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Creating new game", Toast.LENGTH_LONG).show();
                        try{
                            MainActivity host = (MainActivity)getActivity();
                            host.createNewGame(game);
                        }catch (ClassCastException e){
                            Log.d(TAG, getActivity().toString() + " is not mainactivity");
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Creating new game failed", Toast.LENGTH_LONG).show();
                        }
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNeutralButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SelectTeamsDialog selectTeamsDialog = new SelectTeamsDialog();
                        selectTeamsDialog.show(getFragmentManager(), "SelectTeams again");
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
