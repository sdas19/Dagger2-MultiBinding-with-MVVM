 # Paging Library - Architecture Components

Sample created to practice **Paging Library**.

* **Paging Library:** The Paging Library makes it easier for you to load data on demand within your app's RecyclerView.
  
* **PagedList:** Collection that loads data in pages, asynchronously. A PagedList can be used to load data from sources you define, and present it easily in your UI with a RecyclerView.
    
* **DataSource and DataSource.Factory:** DataSource is the base class for loading snapshots of data into a PagedList. A DataSource.Factory is used to create a DataSource.

* **PageKeyedDataSource:** DataSource used to load embed next/previous keys.

* **ItemKeyedDataSource:** DataSource used to retrieve data from item N to fetch item N+1

* **PositionalDataSource:** if you need to fetch pages of data from any location you choose.

* **LivePagedListBuilder:** Class used to build a LiveData<PagedList> based on DataSource.Factory and a PagedList.Config.
  
* **RxPagedListBuilder:** Class used to build a Observable<PagedList> based on DataSource.Factory and a PagedList.Config.
  
* **BoundaryCallback:** Helper callback to signals when a PagedList has reached the end of available data.
    
* **PagedListAdapter:** RecyclerView.Adapter that presents paged data from PagedLists in a RecyclerView. PagedListAdapter listens to PagedList loading callbacks as pages are loaded, and uses DiffUtil to compute fine grained updates as new PagedLists are received.
