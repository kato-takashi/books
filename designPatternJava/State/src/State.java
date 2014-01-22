public interface State {
	public abstract void doClock(Context context, int hour); //時刻設定
	public abstract void doUse(Context context); //金庫使用
	public abstract void doAlarm(Context contex); //非常ベル
	public abstract void doPhone(Context contex); //通常通話
}
