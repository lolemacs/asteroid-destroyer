package com.astrodestroyer.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class PackTextures
{
	public static void main (String[] args) throws Exception 
	{
		TexturePacker.process("./", "atlas", "atlas");
	}
}