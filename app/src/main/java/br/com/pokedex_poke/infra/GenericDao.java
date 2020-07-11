package br.com.pokedex_poke.infra;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.pokedex_poke.dao.generic.FieldBinding;

public class GenericDao<T> implements Dao<T> {

	protected T object;

	public GenericDao(T object) {
		this.object = object;
	}

	@Override
	public void insert(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public T load(Integer id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues toValues(T t) {
		ContentValues values = new ContentValues();

		for (Field field : t.getClass().getDeclaredFields()) {

			FieldBinding fieldBinding = field.getAnnotation(FieldBinding.class);

			if (fieldBinding != null) {

				try {
					values.put(fieldBinding.value(), String.valueOf(field.get(t)));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return values;

	}

	@Override
	public T cursorValues(Cursor c) throws Exception {

		object = (T) object.getClass().newInstance();

		for (Field field : object.getClass().getDeclaredFields()) {

			if (field.isAnnotationPresent(FieldBinding.class)) {

				if (field.getType().equals(Integer.class) || field.getType().equals(int.class))
					object.getClass().getField(field.getName()).set(object,
							c.getInt(c.getColumnIndex(field.getAnnotation(FieldBinding.class).value())));

				else if (field.getType().equals(Double.class) || field.getType().equals(double.class)
						|| field.getType().equals(Float.class) || field.getType().equals(float.class)
						|| field.getType().equals(BigDecimal.class) || field.getType().equals(Number.class))
					object.getClass().getField(field.getName()).set(object,
							c.getDouble(c.getColumnIndex(field.getAnnotation(FieldBinding.class).value())));

				else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class))
					object.getClass().getField(field.getName()).set(object, Boolean.parseBoolean(
							c.getString(c.getColumnIndex(field.getAnnotation(FieldBinding.class).value()))));

				else if (field.getType().equals(Date.class))
					object.getClass().getField(field.getName()).set(object, Date
							.parse(c.getString(c.getColumnIndex(field.getAnnotation(FieldBinding.class).value()))));

				else
					object.getClass().getField(field.getName()).set(object,
							c.getString(c.getColumnIndex(field.getAnnotation(FieldBinding.class).value())));

			}

		}

		return object;
	}

}
