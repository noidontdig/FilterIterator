FilterIterator
===============

My Java implementation of a FilterIterator.

A FilterIterator is an Iterator that can be used to wrap an existing iterator but skip all values that do not satisy a specified criteria.  Requirements include:

* FilterIterator must take an Iterator and Criteria as constructor arguments.
* FilterIterator must behave correctly for all calls to next() and hasNext()
* It should hrow an UnsupportedOperationException in the implementation of remove()
* Criteria should allow users to create their own criteria classes.
* You must implement AndCriteria, OrCriteria, and NotCriteria and include factory methods for easily combining other criteria using these logical criteria.

