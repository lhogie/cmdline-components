package yacm;

public abstract class Source<OUT> extends SingleOutputAgent<OUT> implements Runnable
{

	public Source(String id, ElementParms parms)
	{
		super(id, parms);
	}

}
