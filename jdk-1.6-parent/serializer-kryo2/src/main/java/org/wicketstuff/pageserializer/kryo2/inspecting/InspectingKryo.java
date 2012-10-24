package org.wicketstuff.pageserializer.kryo2.inspecting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Output;

import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;

public class InspectingKryo extends KryoReflectionFactorySupport
{

	private final static Logger LOG = LoggerFactory.getLogger(InspectingKryo.class);
	private final InspectingKryoSerializer parent;

	public InspectingKryo(InspectingKryoSerializer parent)
	{
		this.parent = parent;
	}

	@Override
	public Registration writeClass(Output output, Class type)
	{
		Registration ret;
		before(output, type);
		ret = super.writeClass(output, type);
		after(output, type);
		return ret;
	}

	@Override
	public void writeClassAndObject(Output output, Object object)
	{
		before(output, object);
		super.writeClassAndObject(output, object);
		after(output, object);
	}

	@Override
	public void writeObject(Output output, Object object, Serializer serializer)
	{
		before(output, object);
		super.writeObject(output, object, serializer);
		after(output, object);
	}

	@Override
	public void writeObject(Output output, Object object)
	{
		before(output, object);
		super.writeObject(output, object);
		after(output, object);
	}

	@Override
	public void writeObjectOrNull(Output output, Object object, Class clazz)
	{
		before(output, object);
		super.writeObjectOrNull(output, object, clazz);
		after(output, object);
	}

	@Override
	public void writeObjectOrNull(Output output, Object object, Serializer serializer)
	{
		before(output, object);
		super.writeObjectOrNull(output, object, serializer);
		after(output, object);
	}

	private void before(Output output, Object object)
	{
		parent.serializingListener().before(output.position(), object);
	}

	private void after(Output output, Object object)
	{
		parent.serializingListener().after(output.position(), object);
	}

}
