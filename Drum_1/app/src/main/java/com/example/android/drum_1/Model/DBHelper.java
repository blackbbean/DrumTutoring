package com.example.android.drum_1.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Minjeong Kim on 2018-09-23.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context,"scoreDB",null,DATABASE_VERSION);
    }


    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성

        String scoreSql = "create table scorelist(_id text primary key,rudiment integer,score integer);";
        db.execSQL(scoreSql);
        Log.i("DBHELPER","Created DB");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table scorelist");
            onCreate(db);
        }
    }

    public void insert(String id, int rudiment, int score) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();


        String insertSql ="insert into scorelist values('"+id+"',"+rudiment+","+score+");";
        db.execSQL(insertSql);
        db.close();

        Log.i("DBHELPER","INSERT DB + "+id+"/"+rudiment+"/"+score);

    }




    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM SCORELIST", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getInt(1)
                    + " 번 루디먼트 "
                    + cursor.getInt(2)
                    + "점 \n";

            Log.i("DBHELPER","INSERT DB + "+cursor.getString(0)+"/"+cursor.getInt(1)+"/"+cursor.getInt(2));
        }

        return result;
    }

}
