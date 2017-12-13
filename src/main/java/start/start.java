package start;

import akka.actor.ActorSystem;

import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2017/12/13 0013.
 */
public class start {
	public static void main(String[] args) throws Throwable {
		//创建http服务器
		new MyHttpApp().startServer(
				"localhost",
				12345,
				ActorSystem.create("TheSystem"));
	}
}
