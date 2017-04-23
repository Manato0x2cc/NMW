package com.mnt2cc.BMW.task;

import com.mnt2cc.NMW.magic.Magic;

import cn.nukkit.scheduler.Task;

public class MagicTask extends Task {

	private Magic m;
	
	public MagicTask(Magic m){
		this.m = m;
	}

	@Override
	public void onRun(int tick) {
		this.m.draw();
	}
	
}
