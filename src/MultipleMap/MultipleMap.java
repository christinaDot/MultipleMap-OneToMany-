package MultipleMap;

import java.util.*;


public class MultipleMap
{
	public class Record
	{
		private Object key=null;
		private LinkedList<Object> list=null;

		public Record(Object key, LinkedList<Object> list)
		{
			this.key=key;
			this.list=list;
		}

		public Object getKey()
		{
			return key;
		}

		public LinkedList<Object> getList()
		{
			return list;
		}

	}
	
	private LinkedList<Record> list = null;

	public MultipleMap()
	{
		list = new LinkedList<Record>();
	}

//Checks if the MultipleMap is empty or not.

	public boolean isEmpty()
	{
		if (list.size() == 0)
			return true;
		else
			return false;
	}

// Returns the elements´ number of the multiplemap 
//curList: temporary list just to get the size of the multiplemap
	
	public int size()
	{
		int size=0;

		for (int i=0; i<list.size() ; i++)
		{
			Record rec = list.get(i);
			LinkedList<Object> curList = rec.getList();
			size += curList.size();
		}

	return size;
	}


//Returns the set of values to which this map maps the specified key
	
	public LinkedList<Object> get(Object key)
	{
		for (int i=0; i<list.size() ; i++)
        {
			Record rec = list.get(i);
			Object recKey = rec.getKey();

			if (key.equals(recKey))
				return rec.getList();
        }

	return null;
	}

//Compares the specified object with this multiplemap>two multiplemaps are equal if they contain the same entries

	public boolean equals(MultipleMap m)
	{

		if (this.size() != m.size())
			return false;


		for (int i=0; i<list.size() ; i++)
        {
			Record thisRec = list.get(i);
			Object thisRecKey = thisRec.getKey();
			LinkedList<Object> thisRecList = thisRec.getList();

			LinkedList<Object> mRecList = m.get(thisRecKey);

			if (isEqual(thisRecList, mRecList))
				continue;
			else
				return false;
        }

	return true;
	}


//Checks 2 LinkedLists ignoring the ordering of their elements
	
	private boolean isEqual(LinkedList<Object> l, LinkedList<Object> m)
	{
		if (l.size() != m.size())
			return false;

		for (int i=0; i<l.size() ; i++)
        {
			Object o = l.get(i);

			if (!m.contains(o))
				return false;
		}

	return true;
	}

//Returns a set view of the keys contained in this multiplemap

	public TreeSet<Object> keySet()
	{
		TreeSet<Object> set = new TreeSet<Object>();

		for (int i=0; i<list.size() ; i++)
        {
			Record rec = list.get(i);
			set.add(rec.getKey());
		}
	return set;
	}

//Removes all mappings from this multiplemap
	public void clear()
	{
		list.clear();
	}

//Associates the specified value with the specified key in this multiplemap
	
	public LinkedList<Object> put(Object key, Object value)
	{
		LinkedList<Object> prevList = null;
		boolean keyFound = false;

		if (list.size()>0)
			for (int i=0; i<list.size() ; i++)
        	{
				Record rec = list.get(i);
				Object recKey = rec.getKey();

				if (key.equals(recKey))
				{
					LinkedList<Object> recList = rec.getList();
					prevList = (LinkedList)recList.clone();;
					recList.add(value);
					keyFound = true;
				}
			}

		if (!keyFound)
		{
			LinkedList<Object> recList = new LinkedList<Object>();
			recList.add(value);
			Record rec = new Record(key, recList);
			list.add(rec);
		}

	return prevList;
	}

	private LinkedList<Object> extracted(LinkedList<Object> recList) {
		return (LinkedList<Object>)recList.clone();
	}


// Removes the mapping <key, value> from this multiplemap if it is the current

	public Object remove(Object key, Object value)
	{
		Object prevValue = null;

		for (int i=0; i<list.size() ; i++)
        {
			Record rec = list.get(i);
			Object recKey = rec.getKey();

			if (recKey.equals(key))
			{
				LinkedList<Object> recList = rec.getList();

				for (int j=0 ; j<recList.size() ; j++)
				{
					Object recValue = recList.get(j);
			
					if (recValue.equals(value))
						prevValue = recList.remove(j);
				}
			}
		}
	return prevValue;
	}

//Removes the mappings of this key from this multiplemap if it is the current
	
	public LinkedList<Object> remove(Object key)
	{
		LinkedList<Object> prevList = null;

		for (int i=0; i<list.size() ; i++)
        {
			Record rec = list.get(i);
			Object recKey = rec.getKey();

			if (recKey.equals(key))
			{
				prevList = rec.getList();
				list.remove(i);
			}
		}
	return prevList;
	}

//Replaces the mappings of this MultipleMap with the mappings of the given on the method
	
	public void replace(MultipleMap m)
	{
		list.clear();

		TreeSet<Object> mkeySet = m.keySet();
		Iterator<Object> it = mkeySet.iterator();

		while (it.hasNext())
		{
			Object mKey = it.next();
			LinkedList<Object> mList = m.get(mKey);

			for (int i=0 ; i<mList.size(); i++)
				put(mKey, mList.get(i));
		}

	}


	public void printMultipleMap()
	{
		for (int i=0; i<list.size() ; i++)
        {
			Record rec = list.get(i);
			Object recKey = rec.getKey();

			System.out.print(recKey.toString() + "\t");

			LinkedList<Object> recList = rec.getList();

			for (int j=0 ; j<recList.size() ; j++)
			{
				Object recValue = recList.get(j);
				System.out.print(recValue.toString() + ", ");
			}
			System.out.println();
		}
	}
}