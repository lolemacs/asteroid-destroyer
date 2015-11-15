package com.astrodestroyer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Utils
{
    static public ArrayList<PolygonShape> htmlToVecArray(String content, float scale)
    {
        ArrayList<Vector2[]> v2mat = new ArrayList<Vector2[]>();
        ArrayList<PolygonShape> psArray = new ArrayList<PolygonShape>();

        if (content.contains("area shape")){
            String pattern = "<area(.*)>";
            String pattern2 = "\\d+,\\d+";
            Pattern r = Pattern.compile(pattern);
            Pattern r2 = Pattern.compile(pattern2);
            Matcher m = r.matcher(content);
            while (m.find()) {
                ArrayList<Vector2> v2list = new ArrayList<Vector2>();
                Matcher m2 = r2.matcher(m.group(0));
                while (m2.find()) {
                    String[] parts =m2.group(0).split(",");
                    float x = Integer.parseInt(parts[0]) * scale;
                    float y = Integer.parseInt(parts[1]) * scale;
                    v2list.add(new Vector2(x,y));
                }
                Collections.reverse(v2list);
                Vector2[] v2array = new Vector2[v2list.size()];
                v2array = v2list.toArray(v2array);
                v2mat.add(v2array);

                PolygonShape ps = new PolygonShape();
                ps.set(v2array);
                psArray.add(ps);
            }
        }
        for(Vector2[] v2array : v2mat){
            for(Vector2 v2 : v2array){
                System.out.println(v2.toString());
            }
        }
        return psArray;
    }
}