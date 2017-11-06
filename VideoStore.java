import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Video
{
    String title;
	String id;
	char checkin_status='i';
	Video nextVideo=null;
	Video preVideo=null;
    
	Video()
	{
		
	}
	
	Video(String title,String id){
        this.title=title;
        this.id=id;
		
    }
    
    
    String getTitle()	{
        return this.title;
    }
    
    String getId()    {
        return this.id;
    }
	
	Video getNextVideo(){
		return this.nextVideo;
	}
	Video getPreVideo(){
		return this.preVideo;
	}
	
	void setNextVideo(Video nextVideo){
	this.nextVideo=nextVideo;
	}
	
	void setPreVideo(Video preVideo){

	this.preVideo=preVideo;
	}
}

class Customer
{
	
	String name;
	String id;
	
	ArrayList<Video> rentedVideos=new ArrayList<Video>();
	Customer nextCustomer=null;
	Customer preCustomer=null;
	
	
	Customer()
	{
		
	}
	
	Customer(String name,String id){
		this.name=name;
		this.id=id;
	
		
	}
	
	String getName(){
		return this.name;
	}
	
	String getId(){
		return this.id;
	}
	
	ArrayList getrentVideo(){
		return this.rentedVideos;
	}
	
	
	
	Customer getNextCustomer()
	{
		return this.nextCustomer;
	}
	
	Customer getPreCustomer()
	{
		return this.preCustomer;
	}
	
	void setNextCustomer(Customer next)
	{
	   this.nextCustomer=next;	
	}
	
	void setPreCustomer(Customer prev)
	{
	   this.preCustomer=prev;	
	}
	
	
}


class Video_T_Node
{
Video_T_Node leftVideoNode=null;
Video_T_Node rightVideoNode=null;

String title;
String id;
int keyVal=-1;
char checkin_status='i';

Video_T_Node()
{
	
}

Video_T_Node(String title,String id,int keyVal){
        this.title=title;
        this.id=id;
		this.keyVal=keyVal;
		
    }
	
	
	String getTitle()	{
        return this.title;
    }
    
    String getId()    {
        return this.id;
    }
	
	int getKeyVal()
	{
		return this.keyVal;
	}
	
	Video_T_Node getLeftVideoNode()
	{
	return this.leftVideoNode;
	}
	
	Video_T_Node getRightVideoNode()
	{
	return this.rightVideoNode;
	}
	
	void setLeftVideoNode(Video_T_Node leftVideoNode)
	{
	this.leftVideoNode=leftVideoNode;
	}
	
	void setRightVideoNode(Video_T_Node rightVideoNode)
	{
	this.rightVideoNode=rightVideoNode;
	}
}


class Customer_T_Node
{
Customer_T_Node leftCustomerNode=null;
Customer_T_Node rightCustomerNode=null;

String name;
String id;
int keyCVal=-1;
ArrayList<String> rentedVideos=new ArrayList<String>();

Customer_T_Node()
{
	
}


Customer_T_Node(String name,String id,int keyCVal){
        this.name=name;
        this.id=id;
		this.keyCVal=keyCVal;
		
    }
	
	String getName()	{
        return this.name;
    }
    
    String getId()    {
        return this.id;
    }
	
	int getKeyCVal()
	{
		return this.keyCVal;
	}
	
	Customer_T_Node getLeftCustomerNode()
	{
	return this.leftCustomerNode;
	}
	
	Customer_T_Node getRightCustomerNode()
	{
	return this.rightCustomerNode;
	}
	
	void setLeftCustomerNode(Customer_T_Node leftCustomerNode)
	{
	this.leftCustomerNode=leftCustomerNode;
	}
	
	void setRightCustomerNode(Customer_T_Node rightCustomerNode)
	{
	this.rightCustomerNode=rightCustomerNode;
	}
	
}








class VideoStore_DLL
{
	

	Video videoHeader=null;
	Video videoTrailer=null;
	
	Customer customerHeader=null;
	Customer customerTrailer=null;
	
		
		VideoStore_DLL()
		{
			videoHeader=new Video();
			videoTrailer=new Video();
			
			customerHeader=new Customer();
			customerTrailer=new Customer();
		}
	void check_availablity(String id,int args_length){
		
		Video current=videoHeader;
		current=current.getNextVideo();
		
		
		while(current != null)
		{
			if (current.getId().equals(id) && current.checkin_status=='i')
			{
				if(args_length !=4)
				System.out.println("The video with id "+current.getId()+" and title "+current.getTitle()+" is available in the store");
				break;
			}
			current=current.getNextVideo();
		}
		
		if(current == null ||current.checkin_status=='o')
		if(args_length !=4)	
		System.out.println("The video is not available in the store");
		
		
	}
	
	
	void mark_checkIn(String vid,String cid,int args_length){
		
		Video current=videoHeader;
		current=current.getNextVideo();
		
		while(current != null)
		{   
			
			if(current.getId().equals(vid))
			{
					if(current.checkin_status !='i')
					{
						current.checkin_status='i';
						
						Customer cust_current=customerHeader;
						cust_current=cust_current.getNextCustomer();
						
						boolean isVideoRentedToThisCustomer=false;
						while(cust_current !=null)
						{
							if(cust_current.getId().equals(cid))
							{
								
								for(int i=0;i<cust_current.rentedVideos.size();i++)
								{
									if(cust_current.rentedVideos.get(i)==current)
									{
										isVideoRentedToThisCustomer=true;
										cust_current.rentedVideos.remove(i);
										break;
									}
								}
								break;
							}
							cust_current=cust_current.getNextCustomer();
						}
						
						if(isVideoRentedToThisCustomer==false)
						{
							if(args_length !=4)
							System.out.println("The video is not rented to this customer");
							break;
						}
						
						if(cust_current==null)
						{
							if(args_length !=4)
							System.out.println("There is no such a customer available");
							break;
						}
						
						if(args_length !=4)
						System.out.println(" The video "+current.getTitle()+" from customer "+ cust_current.getName()+" is successfully checked into Video store");
						
									
						break;
					}
					
					else
					{
						if(args_length !=4)
						System.out.println(" The video "+current.getTitle()+" is already checked into Video store.Please Check");
					    break;
					}
				
			}
			current=current.getNextVideo();
		}
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println(" The video is not checked in as it does not belong to the store");
		}
		
			
		
	}
	
	void mark_checkOut(String id,int args_length){
		
		Video current = videoHeader;
		current=current.getNextVideo();
		
		while(current !=null)
		{
			if(current.getId().equals(id))
			{
				current.checkin_status='o';
				//System.out.println("The video "+current.getTitle()+" is checked out");
				break;
			}
			current=current.getNextVideo();
			
		}
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("The video is not available in store");
		}
	}
	

	
	void addVideo(Video videoToAdd,int args_length)
	{
		
		Video current=videoHeader;
					
			
			while(current.getNextVideo()!=null){
				current=current.getNextVideo();
			}
			
			Video prev=current;
			current.setNextVideo(videoToAdd);
			current=current.getNextVideo();
			current.setPreVideo(prev);
			
			
			if(args_length !=4)
			System.out.println("The video "+videoToAdd.getTitle()+" is successfully added to the video store");
			
							
		
	}
	
	void deleteVideo(String videoId)
	{
		Video current=videoHeader;
		Video prev=current;
		current=current.getNextVideo();
			

				while(current!= null)
				{      
						
						String id=current.getId();
						if(id.equals(videoId))
						{
							Video temp=current.getNextVideo();
							prev.setNextVideo(temp);
							temp.setPreVideo(prev);
							System.out.println("The video "+current.getTitle()+" is successfully deleted from store");
							break;
						}
						prev=current;
						current=current.getNextVideo();
				}
				
				if(current==null)
				{
					System.out.println("There is no video available withthe given ID");
				}
			
		
	}
	
	
	void inStoreVideos(){
		
		Video current=videoHeader;
		current=current.getNextVideo();
		
		
		while(current != null)
		{
			if (current.checkin_status=='i')
			{
				System.out.println("Title is : "+current.getTitle()+" Id is : "+current.getId());
			}
			current=current.getNextVideo();
		}
		
		
			System.out.println("*** End of List of In store Videos***");
		
		
	}
	
		void allRentedVideos(){
		
		Video current=videoHeader;
		current=current.getNextVideo();
		
		
		while(current != null)
		{
			if (current.checkin_status=='o')
			{
				System.out.println("Title is : "+current.getTitle()+" Id is : "+current.getId());
			}
			current=current.getNextVideo();
		}
		
		
			System.out.println("*** End of List of Rented Videos***");
		
		
	}
	
	
	
	
		
		
		void printAllVideo()
		{
			Video current=videoHeader;
			current=current.getNextVideo();
			 			
			if(current==null){
				System.out.println("There are no videos in store !!!");
			}
			
			else {
					while(current !=null)
					{
					System.out.println("Title is : "+current.getTitle()+" , check in status is : "+current.checkin_status);						
					current=current.getNextVideo();
					}
			}
			
			
		}
		
		
		
		
		void addCustomer(Customer customerToAdd,int args_length)
		{
			
			Customer current=customerHeader;
					
						
					while(current.getNextCustomer()!=null)
					{
					current=current.getNextCustomer();	
					}
						Customer prev=current;
						current.setNextCustomer(customerToAdd);
						current=current.getNextCustomer();
						current.setPreCustomer(prev);
						if(args_length !=4)
						System.out.println("Customer "+customerToAdd.getName()+" is successfully added to VideoStore Customer List");
							
				
		}
		
		void deleteCustomer(String id)
		{
			Customer current=customerHeader;			
			Customer prev=current;
			current=current.getNextCustomer();
			
			
			if(current.getId().equals(id))
			{
				System.out.println("The customer "+current.getName()+" is successfully deleted from VideoStore CustomerBase list");
				current=current.getNextCustomer();
				prev.setNextCustomer(current);
				current.setPreCustomer(prev);
				
			}
			
			else
			{
							
					while(current != null)
					{
						if(current.getId().equals(id))
						{
						Customer temp=current.getNextCustomer();
						prev.setNextCustomer(temp);
						temp.setPreCustomer(prev);
						System.out.println("The customer "+current.getName()+" is successfully deleted from VideoStore CustomerBase list");
						break;
						}
						prev=current;
						current=current.getNextCustomer();
					}
					
					if(current==null)
					{
						System.out.println("There is no customer available with the given Id");
					}
			}
		}
		
		void rentVideo(String vid,String cid,int args_length)
		{
			Video currVideo=videoHeader;
			currVideo=currVideo.getNextVideo();
			
			Customer currCust=customerHeader;
			currCust=currCust.getNextCustomer();
			
			if(currVideo !=null && currCust !=null)
			{
				while(!currVideo.getId().equals(vid))
				{
					currVideo=currVideo.getNextVideo();
						if(currVideo==null)
						{
							if(args_length !=4)
							System.out.println("There is no video available with the given name");
							break;
						}
				}
				
				while(!currCust.getId().equals(cid))
				{
					currCust=currCust.getNextCustomer();
						if(currCust==null)
						 {
							if(args_length !=4)
							System.out.println("There is no customer available with the given details"); 
							break;
						 }
				}
				
				if(currVideo != null && currCust != null)
				{
				currCust.rentedVideos.add(currVideo);
				mark_checkOut(vid,args_length);
							
				if(args_length !=4)
				System.out.println("The video "+currVideo.getTitle()+" is succesfully rented to the customer "+currCust.getName()); 
				}
					 
			}

				else if(currVideo==null){
					if(args_length !=4)
					System.out.println("Video store has no videos available in store");
				}
				else{
					if(args_length !=4)
					System.out.println("Video store has no customers registered");
				}
			
			
		}
		
	
		
		void rentedByCustomer(String id)
		{
			Customer current=customerHeader;
			current=current.getNextCustomer();
			
			while(current != null)
			{ 
		        if(current.getId().equals(id))
				{
					for(int i=0;i<current.rentedVideos.size();i++)
					{
					System.out.println(current.rentedVideos.get(i).getTitle());
					}
					break;
				}
				
				if(current==null)
				{
					System.out.println("There is no customer available with the given Id");
					break;
				}
				current=current.getNextCustomer();
			}
		}	
		
		void printCustomerBase()
		{
			
			Customer current=customerHeader;
			current=current.getNextCustomer();
			
			while(current != null)
			{
				System.out.println(current.getName());
				current=current.getNextCustomer();
			}
			
			System.out.println("***End of Customer base***");
			
		}
		
}






class VideoStore_BST
{
	
	static Video_T_Node v_root=new Video_T_Node();
	static Customer_T_Node c_root=new Customer_T_Node();
	static ArrayList<Integer> allRentedVideos = new ArrayList<Integer>();
	
	
	
	void addVideo(Video_T_Node videoToAdd,int args_length)
	{
		
		if(v_root.keyVal==-1)
		{
			v_root=videoToAdd;
			if(args_length !=4)
			System.out.println("Video with title : "+v_root.getTitle()+" ,id :"+v_root.getId()+" ,key value : "+v_root.getKeyVal()+" is successfully added to video store.");
			
		}
		else
		{
			
			
			Video_T_Node current=v_root;
			Video_T_Node parent=null;
			boolean left=false;
			boolean right=false;
			
			while(current !=null)
			{
			
					if(current.keyVal>videoToAdd.keyVal)
					{
						parent=current;
						current=current.leftVideoNode;
						left=true;
						right=false;
							
					}
					else
					{
						parent=current;
						current=current.rightVideoNode;
						right=true;
						left=false;
						
					}
			}
			current=videoToAdd;
			if(left==true){
				parent.leftVideoNode=current;
				
			}
			else
			{
				parent.rightVideoNode=current;
				
			}
			
			if(args_length !=4)
			System.out.println("Video with title : "+current.getTitle()+" ,id :"+current.getId()+" ,key value : "+current.getKeyVal()+" is successfully added to video store.");
		}
		
	}
	
	void printVideo()
	{
		Video_T_Node current=v_root;
		
		if(current==null)
		{
			System.out.println("There are No videos stored in the video store !!!");
		}
		else
		{
				System.out.println("Video title ,id & key values are :");	
				current=v_root;
				System.out.println("Title : "+current.getTitle()+", Id : "+current.getId()+", Key value :"+current.getKeyVal());
				
				
				
					
										
						Video_T_Node curr_left= current.leftVideoNode;
						Video_T_Node curr_right= current.rightVideoNode;
						if(curr_left !=null)
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						printingLoop(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						printingLoop(current);
						}
				
		}
	}
	
	void printingLoop(Video_T_Node current)
	{
						Video_T_Node curr_left= current.leftVideoNode;
						Video_T_Node curr_right= current.rightVideoNode;
						if(curr_left !=null)
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						current=curr_left;
						if(current !=null)
							printingLoop(current);
						
						current=curr_right;
						if(current != null)
							printingLoop(current);
		
	}
	
	
	void deleteVideo(int keyVal)
	{
		Video_T_Node current=v_root;
		Video_T_Node parent=v_root;
		
		while(current !=null)
		{
					
				
				if(current.keyVal==keyVal)
				{
					
					if(current.checkin_status=='o')
					{
						System.out.println(" This video is rented to customer and not available in store to remove.");
						break;
					}
					
					
					Video_T_Node rightChild=current.rightVideoNode;
					Video_T_Node leftChild=current.leftVideoNode;
					
					
					Video_T_Node temp=current;
					
					if( (rightChild !=null && leftChild !=null) || (rightChild !=null & leftChild ==null))
					{
						
							while(rightChild.leftVideoNode != null)
							{
								temp=rightChild;
								rightChild=rightChild.leftVideoNode;
							}
							
							current.keyVal=rightChild.keyVal;
														
							
							if(rightChild.rightVideoNode != null)
							{
								if(temp.keyVal>rightChild.keyVal)
								temp.leftVideoNode=rightChild.rightVideoNode;
							
								else
								temp.rightVideoNode=rightChild.rightVideoNode;
							}
							
							else
							{
							
								if(temp.keyVal>rightChild.keyVal)
									temp.leftVideoNode=null;

								else								
									temp.rightVideoNode=null;
							}
							
					System.out.println("Video successfully removed from store !!!");
					break;
					}
					
					
					else if(rightChild ==null & leftChild !=null)
					{
						leftChild=current.leftVideoNode;
						temp=leftChild;
							while(leftChild.rightVideoNode !=null)
							{
								
								leftChild=leftChild.rightVideoNode;
							}
							
							current.keyVal=leftChild.keyVal;
							if(parent.keyVal<temp.keyVal)
								parent.rightVideoNode=current.leftVideoNode;
							else
								parent.leftVideoNode=current.leftVideoNode;
							
					System.out.println("Video successfully removed from store !!!");
					break;		
					}
					else if(rightChild ==null & leftChild ==null)
					{
						if(parent.keyVal > current.keyVal)
							parent.leftVideoNode=null;
						else
							parent.rightVideoNode=null;
						
					}
					System.out.println("Video successfully removed from store !!!");
					break;
					
				}
				else if(current.keyVal<keyVal){
				
					parent =current;
					current=current.rightVideoNode;
				
				}
				
				else
				{
					parent=current;
					current=current.leftVideoNode;			
				}
		}//while
		
		if(current==null)
		System.out.println("There is no video with the given details !!!");
	}
	
	
	void check_availablity(int keyVal,int args_length)
	{
		
		Video_T_Node current=v_root;
		Video_T_Node parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				if(current.checkin_status=='i')
				{
					if(args_length !=4)
					System.out.println("Video is available in the store.");
				}
				else
				{
					if(args_length !=4)
					System.out.println("Video is rented out to customer");	
				}
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("Video with given detials is not registered in store.");
		}
		
		
	}
	
	
	
	void addCustomer(Customer_T_Node customerToAdd,int args_length)
	{
		
		
		if(c_root.keyCVal==-1)
		{
			c_root=customerToAdd;
			if(args_length !=4)
			System.out.println("Customer name :"+c_root.getName()+" ,Id : "+c_root.getId()+" ,KeyValue :"+c_root.getKeyCVal()+" is succesfully registered in the video store.");
			
		}
		else
		{
			
			
			Customer_T_Node current=c_root;
			Customer_T_Node parent=null;
			boolean left=false;
			boolean right=false;
			
			while(current !=null)
			{
			
					if(current.keyCVal>customerToAdd.keyCVal)
					{
						parent=current;
						current=current.leftCustomerNode;
						left=true;
						right=false;
							
					}
					else
					{
						parent=current;
						current=current.rightCustomerNode;
						right=true;
						left=false;
						
					}
			}
			current=customerToAdd;
			if(left==true){
				parent.leftCustomerNode=current;
				
			}
			else
			{
				parent.rightCustomerNode=current;
				
			}
		if(args_length !=4)
		System.out.println("Customer name :"+current.getName()+" ,Id : "+current.getId()+" ,KeyValue :"+current.getKeyCVal()+" is succesfully registered in the video store.");
		}
		
		
	}
	
	
	void printCustomer()
	{
		Customer_T_Node current=c_root;
		
		if(current==null)
		{
			System.out.println("There are No Customers registered in the video store !!!");
		}
		else
		{
						
				current=c_root;
				System.out.println("Customer names ,id & key values are :");
				System.out.println("Name : "+current.getName()+", Id : "+current.getId()+", Key value :"+current.getKeyCVal());
					
										
						Customer_T_Node curr_left= current.leftCustomerNode;
						Customer_T_Node curr_right= current.rightCustomerNode;
						if(curr_left !=null)
							System.out.println("Name : "+curr_left.getName()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyCVal());
						if(curr_right !=null)
							System.out.println("Name : "+curr_right.getName()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyCVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						printingLoop_Customer(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						printingLoop_Customer(current);
						}
				
		}
	}
	
	void printingLoop_Customer(Customer_T_Node current)
	{
						Customer_T_Node curr_left= current.leftCustomerNode;
						Customer_T_Node curr_right= current.rightCustomerNode;
						if(curr_left !=null)
							System.out.println("Name : "+curr_left.getName()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyCVal());
						if(curr_right !=null)
							System.out.println("Name : "+curr_right.getName()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyCVal());
						
						current=curr_left;
						if(current !=null)
							printingLoop_Customer(current);
						
						current=curr_right;
						if(current != null)
							printingLoop_Customer(current);
		
	}
	
	void deleteCustomer(int keyCVal)
	{
		Customer_T_Node current=c_root;
		Customer_T_Node parent=c_root;
		
		while(current !=null)
		{
					
				
				if(current.keyCVal==keyCVal)
				{
					if(current.rentedVideos.size() !=0)
					{
						System.out.println("Customer has rented some videos from the store and can not be delted from store until all videos are returned !!!");
						break;
					}						
					
					Customer_T_Node rightChild=current.rightCustomerNode;
					Customer_T_Node leftChild=current.leftCustomerNode;
					
					
					Customer_T_Node temp=current;
					
					if( (rightChild !=null && leftChild !=null) || (rightChild !=null & leftChild ==null))
					{
						
							while(rightChild.leftCustomerNode != null)
							{
								temp=rightChild;
								rightChild=rightChild.leftCustomerNode;
							}
							
							current.keyCVal=rightChild.keyCVal;
													
							
							if(rightChild.rightCustomerNode != null)
							{
								if(temp.keyCVal>rightChild.keyCVal)
								temp.leftCustomerNode=rightChild.rightCustomerNode;
							
								else
								temp.rightCustomerNode=rightChild.rightCustomerNode;
							}
							
							else
							{
							
								if(temp.keyCVal>rightChild.keyCVal)
									temp.leftCustomerNode=null;

								else								
									temp.rightCustomerNode=null;
							}
							
					System.out.println("Customer deleted succesfully from video store.");
					break;
					}
					
					
					else if(rightChild ==null & leftChild !=null)
					{
						leftChild=current.leftCustomerNode;
						temp=leftChild;
							while(leftChild.rightCustomerNode !=null)
							{
								
								leftChild=leftChild.rightCustomerNode;
							}
							
							current.keyCVal=leftChild.keyCVal;
							if(parent.keyCVal<temp.keyCVal)
								parent.rightCustomerNode=current.leftCustomerNode;
							else
								parent.leftCustomerNode=current.leftCustomerNode;
					
					System.out.println("Customer deleted succesfully from video store.");		
					break;		
					}
					else if(rightChild ==null & leftChild ==null)
					{
						if(parent.keyCVal > current.keyCVal)
							parent.leftCustomerNode=null;
						else
							parent.rightCustomerNode=null;
						
					}
					System.out.println("Customer deleted succesfully from video store.");
					break;
					
				}
				else if(current.keyCVal<keyCVal){
				
					parent =current;
					current=current.rightCustomerNode;
				
				}
				
				else
				{
					parent=current;
					current=current.leftCustomerNode;			
				}
		}//while
		
		if(current==null)
			System.out.println("There is no customer with the given details.");
	}
	
	
	boolean searchCustomer(int keyCVal)
	{
		boolean search=false;
		Customer_T_Node current=c_root;
		Customer_T_Node parent=c_root;
		
		while(current !=null)
		{
			
			if(current.keyCVal==keyCVal)
			{
				search=true;
				break;
			}
			else if(current.keyCVal> keyCVal)
			{
				parent=current;
				current=current.leftCustomerNode;
			}
			else if(current.keyCVal<keyCVal)
			{
				parent=current;
				current=current.rightCustomerNode;
			}
		}
		
		return search;
		
		
	}
	
	
	static void checkOutVideo(int keyVal,int keyCVal,int args_length)
	{
		Video_T_Node current=v_root;
		Video_T_Node parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				
				
				Customer_T_Node current_Cust=c_root;
				Customer_T_Node parent_Cust=c_root;
		
						while(current_Cust !=null)
						{
							
							if(current_Cust.keyCVal==keyCVal)
							{
								if(current.checkin_status=='i')
								{
									current.checkin_status='o';
									allRentedVideos.add(current.keyVal);
									current_Cust.rentedVideos.add(current.id);
									
									if(args_length !=4)
									System.out.println("The video id : "+current.id+" is successfully rented to customer with id : "+current_Cust.id);
								}
								else
								{
									if(args_length !=4)
									System.out.println("The video : "+current.id+" is already rented to other customer ");
								}
								break;
							
							}
							else if(current_Cust.keyCVal> keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.leftCustomerNode;
							}
							else if(current_Cust.keyCVal<keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.rightCustomerNode;
							}
						}
						
		
		
						if(current_Cust==null)
						{
							if(args_length !=4)
							System.out.println("There is no customer available with the given details !!!");
							break;
						}
				
				
				
				
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("There is no video available with the given details !!!");
							
		}
		
	}
	
	
	static void allRentedVideos()
	{
		if(allRentedVideos.size()>=1)
		{
			System.out.println("The KeyValues of rented videos are :");
			for(int i=0;i<allRentedVideos.size();i++)
			{
				System.out.println(allRentedVideos.get(i));
			}
		}
		else
			System.out.println("currently there are no rented videos !!! ");
		
		
		
	}

	
	
	
	
	
	static void checkInVideo(int keyVal,int keyCVal,int args_length)
	{
		Video_T_Node current=v_root;
		Video_T_Node parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				
				
				Customer_T_Node current_Cust=c_root;
				Customer_T_Node parent_Cust=c_root;
		
						while(current_Cust !=null)
						{
							
							if(current_Cust.keyCVal==keyCVal)
							{
								if(current.checkin_status=='o')
								{
									current.checkin_status='i';
												
												for(int i=0;i<allRentedVideos.size();i++)
												{
													 if(keyVal==allRentedVideos.get(i))
													 {
														 allRentedVideos.remove(i);
														 break;
													 }
												}
												
												for(int i=0;i<current_Cust.rentedVideos.size();i++)
												{
													 if(current.id.equals(current_Cust.rentedVideos.get(i)))
													 {
														 current_Cust.rentedVideos.remove(i);
														 break;
													 }
												}
									
																				
									if(args_length !=4)
									System.out.println("The video id: "+current.id+" is successfully checked in from customer id : "+current_Cust.id);
								}
								else
								{
									if(args_length !=4)
									System.out.println("The video : "+current.id+" is already checked in to video store ");
								}
								break;
							
							}
							else if(current_Cust.keyCVal> keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.leftCustomerNode;
							}
							else if(current_Cust.keyCVal<keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.rightCustomerNode;
							}
						}
						
		
		
						if(current_Cust==null)
						{
							if(args_length !=4)
							System.out.println("There is no customer available with the given details !!!");
							break;
						}
				
				
				
				
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("There is no video available with the given details !!!");
							
		}
		
	}
	
	
	
	
	
	void inStoreVideos()
	{
		Video_T_Node current=v_root;
		
		if(current==null)
		{
			System.out.println("There are No videos stored in the video store !!!");
		}
		else
		{
					
				current=v_root;
				if(current.checkin_status=='i')
				{
				System.out.println("Video title ,id & key values are :");
				System.out.println("Title : "+current.getTitle()+", Id : "+current.getId()+", Key value :"+current.getKeyVal());
				}
				
				
				
					
										
						Video_T_Node curr_left= current.leftVideoNode;
						Video_T_Node curr_right= current.rightVideoNode;
						if(curr_left !=null)
							if(curr_left.checkin_status=='i')
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							if(curr_right.checkin_status=='i')
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						traverseLoop(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						traverseLoop(current);
						}
				
		}
	}
	
	void traverseLoop(Video_T_Node current)
	{
						Video_T_Node curr_left= current.leftVideoNode;
						Video_T_Node curr_right= current.rightVideoNode;
						if(curr_left !=null)
							if(curr_left.checkin_status=='i')
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							if(curr_right.checkin_status=='i')
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						current=curr_left;
						if(current !=null)
							traverseLoop(current);
						
						current=curr_right;
						if(current != null)
							traverseLoop(current);
		
	}
	
	
	
	
	
	static void rentedByCustomer(int keyCVal)
	{
	

		Customer_T_Node current=c_root;
		Customer_T_Node parent=c_root;
		
		while(current !=null)
		{
			
			if(current.keyCVal==keyCVal)
			{
				if(current.rentedVideos.size()>=1)	
				{
					System.out.println("The videos rented by customer i : "+current.id+" is/ are :");
					for(int i=0;i<current.rentedVideos.size();i++)
					{
						System.out.println(current.rentedVideos.get(i));
					}
				}
				else
				System.out.println("There are no videos rented by this customer !!!");
				
				break;
			}
			else if(current.keyCVal> keyCVal)
			{
				parent=current;
				current=current.leftCustomerNode;
			}
			else if(current.keyCVal<keyCVal)
			{
				parent=current;
				current=current.rightCustomerNode;
			}
		}
		
		
		if(current==null)
			System.out.println("There is no customer with the given detials !!!");






	
	}
	
	
}

class VideoStore_SLL
{
	
	
	Video videoHead=null;
	Customer customerHead=null;
	
	
	
	void check_availablity(String id,int args_length){
		
		Video current=videoHead;
		
		
		while(current != null)
		{
			if (current.getId().equals(id) && current.checkin_status=='i')
			{
				if(args_length !=4)
				System.out.println("The video with id "+current.getId()+" and title "+current.getTitle()+" is available in the store");
				break;
			}
			current=current.getNextVideo();
		}
		
		if(current == null ||current.checkin_status=='o')
		if(args_length !=4)	
		System.out.println("The video is not available in the store");
		
		
	}
	
	
	void mark_checkIn(String vid,String cid,int args_length){
		
		Video current=videoHead;
		
		while(current != null)
		{   
			
			if(current.getId().equals(vid))
			{
					if(current.checkin_status !='i')
					{
						current.checkin_status='i';
						
						Customer cust_current=customerHead;
						boolean isVideoRentedToThisCustomer=false;
						while(cust_current !=null)
						{
							if(cust_current.getId().equals(cid))
							{
								
								for(int i=0;i<cust_current.rentedVideos.size();i++)
								{
									if(cust_current.rentedVideos.get(i)==current)
									{
										isVideoRentedToThisCustomer=true;
										cust_current.rentedVideos.remove(i);
									}
								}
								break;
							}
							cust_current=cust_current.getNextCustomer();
						}
						
						if(isVideoRentedToThisCustomer==false)
						{
							if(args_length !=4)
							System.out.println("The video is not rented to this customer");
							break;
						}
						
						if(cust_current==null)
						{
							if(args_length !=4)
							System.out.println("There is no such a customer available");
							break;
						}
						
						if(args_length !=4)
						System.out.println(" The video "+current.getTitle()+" from customer "+ cust_current.getName()+" is successfully checked into Video store");
						
									
						break;
					}
					
					else
					{
						if(args_length !=4)
						System.out.println(" The video "+current.getTitle()+" is already checked into Video store.Please Check");
					    break;
					}
				
			}
			current=current.getNextVideo();
		}
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println(" The video is not checked in as it does not belong to the store");
		}
		
			
		
	}
	
	void mark_checkOut(String id,int args_length){
		
		Video current = videoHead;
		
		while(current !=null)
		{
			if(current.getId().equals(id))
			{
				current.checkin_status='o';
				//System.out.println("The video "+current.getTitle()+" is checked out");
				break;
			}
			current=current.getNextVideo();
			
		}
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("The video is not available in store");
		}
	}
	
	void addVideo(Video videoToAdd,int args_length){
		
		
			if(videoHead==null)
			{
				videoHead=videoToAdd;
				if(args_length !=4)
				System.out.println("The video "+videoHead.getTitle()+" is successfully added to the video store-head");
			}
			else
			{
			Video current=videoHead;
			
			while(current.getNextVideo() !=null){
				current=current.getNextVideo();
			}
			
			current.setNextVideo(videoToAdd);
			if(args_length !=4)
			System.out.println("The video "+videoToAdd.getTitle()+" is successfully added to the video store");
			}			
		
	}
	
	void deleteVideo(String videoId)
	{
		Video current=videoHead;
		if(current==null)
		{
			System.out.println("There are No videos to delete");
		}
		
		else if(current.getId().equals(videoId))
		{
			
		videoHead=current.getNextVideo();
		System.out.println("The video "+current.getTitle()+" is successfully deleted from store");
		}
			
			else{ 
				
				Video prev=current;
				while(current!= null)
				{      
						
						String id=current.getId();
						if(id.equals(videoId))
						{
							Video temp=current.getNextVideo();
							prev.setNextVideo(temp);
							System.out.println("The video "+current.getTitle()+" is successfully deleted from store");
						}
						prev=current;
						current=current.getNextVideo();
				}
			}
		
	}
	
	
	void inStoreVideos(){
		
		Video current=videoHead;
		
		
		while(current != null)
		{
			if (current.checkin_status=='i')
			{
				System.out.println("Title is : "+current.getTitle()+" Id is : "+current.getId());
			}
			current=current.getNextVideo();
		}
		
		
			System.out.println("*** End of List of In store Videos***");
		
		
	}
	
		void allRentedVideos(){
		
		Video current=videoHead;
		
		
		while(current != null)
		{
			if (current.checkin_status=='o')
			{
				System.out.println("Title is : "+current.getTitle()+" Id is : "+current.getId());
			}
			current=current.getNextVideo();
		}
		
		
			System.out.println("*** End of List of Rented Videos***");
		
		
	}
	
			
		void printAllVideo()
		{
			Video current=videoHead;
			 			
			if(current==null){
				System.out.println("There are no videos in store !!!");
			}
			
			else {
					while(current !=null)
					{
					System.out.println("Title is : "+current.getTitle()+" , check in status is : "+current.checkin_status);						
					current=current.getNextVideo();
					}
			}
			
			
		}
		
		
		
		void addCustomer(Customer customerToAdd,int args_length)
		{
			
			Customer current=customerHead;
			if(current==null)
			{
					customerHead=customerToAdd;
					if(args_length !=4)
					System.out.println("Customer "+customerToAdd.getName()+" is successfully added to VideoStore Customer List");
			}
			
			else{
			
					while(current.getNextCustomer()!=null)
					{
					current=current.getNextCustomer();	
					}
						
						current.setNextCustomer(customerToAdd);
						if(args_length !=4)
						System.out.println("Customer "+customerToAdd.getName()+" is successfully added to VideoStore Customer List");
							
				}
		}
		
		void deleteCustomer(String id)
		{
			Customer current=customerHead;
			Customer prev=customerHead;
			
			if(current.getId().equals(id))
			{
				customerHead=current.getNextCustomer();
				System.out.println("The customer "+current.getName()+" is successfully deleted from VideoStore CustomerBase list");
			}
			else
			{
			
					while(current != null)
					{
						if(current.getId().equals(id))
						{
						Customer temp=current.getNextCustomer();
						prev.setNextCustomer(temp);
						System.out.println("The customer "+current.getName()+" is successfully deleted from VideoStore CustomerBase list");
						break;
						}
						prev=current;
						current=current.getNextCustomer();
					}
							
			}
		}
		
		void rentVideo(String vid,String cid,int args_length)
		{
			Video currVideo=videoHead;
			Customer currCust=customerHead;
			
			if(currVideo !=null && currCust !=null)
			{
				while(!currVideo.getId().equals(vid))
				{
					currVideo=currVideo.getNextVideo();
						if(currVideo==null)
						{
							if(args_length !=4)
							System.out.println("There is no video available with the given name");
							break;
						}
				}
				
				while(!currCust.getId().equals(cid))
				{
					currCust=currCust.getNextCustomer();
						if(currCust==null)
						 {
							if(args_length !=4)
							System.out.println("There is no customer available with the given details"); 
							break;
						 }
				}
				
				if(currVideo != null && currCust != null)
				{
				currCust.rentedVideos.add(currVideo);
				mark_checkOut(vid,args_length);
							
				if(args_length !=4)
				System.out.println("The video "+currVideo.getTitle()+" is succesfully rented to the customer "+currCust.getName()); 
				}
					 
			}

				else if(currVideo==null){
					if(args_length !=4)
					System.out.println("Video store has no videos available in store");
				}
				else{
					if(args_length !=4)
					System.out.println("Video store has no customers registered");
				}
			
			
		}
		
		void rentedVideos()
		{
			Video curr_Video=videoHead;
			while(curr_Video != null )
			{
				if(curr_Video.checkin_status=='o')
				{
					System.out.println("The video "+curr_Video.getTitle()+" is rented");
				}
				curr_Video=curr_Video.getNextVideo();
			}
		}
		
		void rentedByCustomer(String id)
		{
			Customer current=customerHead;
			while(current != null)
			{ 
		        if(current.getId().equals(id))
				{
					for(int i=0;i<current.rentedVideos.size();i++)
					{
					System.out.println(current.rentedVideos.get(i).getTitle());
					}
					break;
				}
				
				if(current==null)
				{
					System.out.println("There is no such a customer");
				}
				current=current.getNextCustomer();
			}
		}	
		
		void printCustomerBase()
		{
			
			Customer current=customerHead;
			while(current != null)
			{
				System.out.println(current.getName());
				current=current.getNextCustomer();
			}
			
			System.out.println("***End of Customer base***");
			
		}
		
}


class Video_T_Node_AVL
{
Video_T_Node_AVL parentVideoNode=null;
Video_T_Node_AVL leftVideoNode=null;
Video_T_Node_AVL rightVideoNode=null;

String title;
String id;
int keyVal=-1;
char checkin_status='i';

Video_T_Node_AVL()
{
	
}

Video_T_Node_AVL(String title,String id,int keyVal){
        this.title=title;
        this.id=id;
		this.keyVal=keyVal;
		
    }
	
	
	String getTitle()	{
        return this.title;
    }
    
    String getId()    {
        return this.id;
    }
	
	int getKeyVal()
	{
		return this.keyVal;
	}
	
	Video_T_Node_AVL getParentVideoNode()
	{
	return this.parentVideoNode;
	}
	
	Video_T_Node_AVL getLeftVideoNode()
	{
	return this.leftVideoNode;
	}
	
	Video_T_Node_AVL getRightVideoNode()
	{
	return this.rightVideoNode;
	}
	
	void setLeftVideoNode(Video_T_Node_AVL leftVideoNode)
	{
	this.leftVideoNode=leftVideoNode;
	}
	
	void setParentVideoNode(Video_T_Node_AVL parentVideoNode)
	{
	this.parentVideoNode=parentVideoNode;
	}
	
	void setRightVideoNode(Video_T_Node_AVL rightVideoNode)
	{
	this.rightVideoNode=rightVideoNode;
	}
}


class Customer_T_Node_AVL
{
Customer_T_Node_AVL parentCustomerNode=null;
Customer_T_Node_AVL leftCustomerNode=null;
Customer_T_Node_AVL rightCustomerNode=null;

String name;
String id;
int keyCVal=-1;
ArrayList<String> rentedVideos=new ArrayList<String>();

Customer_T_Node_AVL()
{
	
}


Customer_T_Node_AVL(String name,String id,int keyCVal){
        this.name=name;
        this.id=id;
		this.keyCVal=keyCVal;
		
    }
	
	String getName()	{
        return this.name;
    }
    
    String getId()    {
        return this.id;
    }
	
	int getKeyCVal()
	{
		return this.keyCVal;
	}
	
	Customer_T_Node_AVL getParentCustomerNode()
	{
	return this.parentCustomerNode;
	}
	
	Customer_T_Node_AVL getLeftCustomerNode()
	{
	return this.leftCustomerNode;
	}
	
	Customer_T_Node_AVL getRightCustomerNode()
	{
	return this.rightCustomerNode;
	}
	
	void setParentCustomerNode(Customer_T_Node_AVL parentCustomerNode)
	{
	this.parentCustomerNode=parentCustomerNode;
	}
	
	void setLeftCustomerNode(Customer_T_Node_AVL leftCustomerNode)
	{
	this.leftCustomerNode=leftCustomerNode;
	}
	
	void setRightCustomerNode(Customer_T_Node_AVL rightCustomerNode)
	{
	this.rightCustomerNode=rightCustomerNode;
	}
	
}





class VideoStore_AVL
{
	
	static Video_T_Node_AVL v_root=new Video_T_Node_AVL();
	static Customer_T_Node_AVL c_root=new Customer_T_Node_AVL();
	static ArrayList<Integer> allRentedVideos = new ArrayList<Integer>();
	
	
	
	void addVideo(Video_T_Node_AVL videoToAdd,int args_length)
	{
		
		if(v_root.keyVal==-1)
		{
			v_root=videoToAdd;
			if(args_length !=4)
			System.out.println("Video with title : "+v_root.getTitle()+" ,id :"+v_root.getId()+" ,key value : "+v_root.getKeyVal()+" is successfully added to video store.");
			
		}
		else
		{
			
			
			Video_T_Node_AVL current=v_root;
			Video_T_Node_AVL parent=null;
			boolean left=false;
			boolean right=false;
			
			while(current !=null)
			{
				
			
					if(current.keyVal>videoToAdd.keyVal)
					{
						parent=current;
						current=current.leftVideoNode;
						left=true;
						right=false;
						
							
					}
					else
					{
						parent=current;
						current=current.rightVideoNode;
						right=true;
						left=false;
						
					}
			}
			current=videoToAdd;
			current.setParentVideoNode(parent);
			if(left==true){
				parent.setLeftVideoNode(current);
				
			}
			else
			{
				parent.setRightVideoNode(current);
				
			}
			
			
			reArrange(current);
			if(args_length !=4)
			System.out.println("Video with title : "+current.getTitle()+" ,id :"+current.getId()+" ,key value : "+current.getKeyVal()+" is successfully added to video store.");
		}
		
		
	}
	
	
	void reArrange(Video_T_Node_AVL videoToAdd)
	{
		Video_T_Node_AVL grandParent=v_root;
		Video_T_Node_AVL parent=v_root;
		Video_T_Node_AVL current=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==videoToAdd.keyVal)
			{
				
				if(grandParent != parent)
				{ 
						if(grandParent.rightVideoNode==parent && parent.rightVideoNode==current && grandParent.leftVideoNode==null)
						{
							
							Video_T_Node_AVL tempgp=grandParent.getParentVideoNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightVideoNode.getKeyVal()==grandParent.getKeyVal())
									tempgp.setRightVideoNode(parent);
								else
									tempgp.setLeftVideoNode(parent);
							}
								
							if(grandParent.getKeyVal() !=v_root.getKeyVal())
							{
							grandParent.setParentVideoNode(parent);
							grandParent.setRightVideoNode(null);
							parent.setParentVideoNode(tempgp);
							parent.setLeftVideoNode(grandParent);
							break;
							}
							
							else
							{
								Video_T_Node_AVL temp=v_root;
								parent.setLeftVideoNode(v_root);
								v_root.setRightVideoNode(null);
								v_root.setParentVideoNode(parent);
								v_root=parent;
							}
						}
						
						else if(grandParent.leftVideoNode==parent && parent.leftVideoNode==current && grandParent.rightVideoNode==null)
						{
							
							
							Video_T_Node_AVL tempgp=grandParent.getParentVideoNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightVideoNode.getKeyVal()==grandParent.getKeyVal())
									tempgp.setRightVideoNode(parent);
								else
									tempgp.setLeftVideoNode(parent);
							}
							
							
							if(grandParent.getKeyVal() !=v_root.getKeyVal())
							{
							grandParent.setParentVideoNode(parent);
							grandParent.setLeftVideoNode(null);
							parent.setParentVideoNode(tempgp);
							parent.setRightVideoNode(grandParent);
							break;
							}
							
							else
							{
								Video_T_Node_AVL temp=v_root;
								parent.setRightVideoNode(v_root);
								v_root.setLeftVideoNode(null);
								v_root.setParentVideoNode(parent);
								v_root=parent;
								break;
							}
							
						}
						
						else if(grandParent.rightVideoNode==parent && parent.leftVideoNode==current && grandParent.leftVideoNode==null)
						{
							
							
							Video_T_Node_AVL tempgp=grandParent.getParentVideoNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightVideoNode.getKeyVal()==grandParent.getKeyVal())
									tempgp.setRightVideoNode(current);
								else
									tempgp.setLeftVideoNode(current);
							}
								
							if(grandParent.getKeyVal() !=v_root.getKeyVal())
							{
							grandParent.setParentVideoNode(current);
							grandParent.setRightVideoNode(null);
							parent.setParentVideoNode(current);
							current.setRightVideoNode(parent);
							current.setLeftVideoNode(grandParent);
							parent.setLeftVideoNode(null);
							current.setParentVideoNode(tempgp);
							break;
							}
							
							else
							{
								Video_T_Node_AVL temp=v_root;
								parent.setLeftVideoNode(null);
								v_root.setRightVideoNode(null);
								current.setLeftVideoNode(grandParent);
								current.setRightVideoNode(parent);
								current.setParentVideoNode(null);
								grandParent.setParentVideoNode(current);
								parent.setParentVideoNode(current);
								v_root=current;
							}
							
						}
						
						else if(grandParent.leftVideoNode==parent && parent.rightVideoNode==current && grandParent.rightVideoNode==null)
						{						
		
							
							
							Video_T_Node_AVL tempgp=grandParent.getParentVideoNode();
							
							
							if(tempgp != null)
							{
								if(tempgp.rightVideoNode.getKeyVal()==grandParent.getKeyVal())
									tempgp.setRightVideoNode(current);
								else
									tempgp.setLeftVideoNode(current);
							}
								
							if(grandParent.getKeyVal() !=v_root.getKeyVal())
							{
							grandParent.setParentVideoNode(current);
							grandParent.setLeftVideoNode(null);
							parent.setParentVideoNode(current);
							current.setRightVideoNode(grandParent);
							current.setLeftVideoNode(parent);
							parent.setRightVideoNode(null);
							current.setParentVideoNode(tempgp);
							break;
							}
							
							else
							{
								Video_T_Node_AVL temp=v_root;
								parent.setRightVideoNode(null);
								v_root.setLeftVideoNode(null);
								current.setLeftVideoNode(parent);
								current.setRightVideoNode(grandParent);
								parent.setParentVideoNode(current);
								grandParent.setParentVideoNode(current);
								v_root=current;
							}
						
						}
							
				
				}
				break;
			}
			else if(current.keyVal> videoToAdd.keyVal)
			{
				grandParent=parent;
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<videoToAdd.keyVal)
			{
				grandParent=parent;
				parent=current;
				current=current.rightVideoNode;
			}
		}
		
		
	}
	
	void printVideo()
	{
		Video_T_Node_AVL current=v_root;
		
		if(current==null)
		{
			System.out.println("There are No videos stored in the video store !!!");
		}
		else
		{
				System.out.println("Video title ,id & key values are :");	
				current=v_root;
				System.out.println("Title : "+current.getTitle()+", Id : "+current.getId()+", Key value :"+current.getKeyVal());
				
								
						Video_T_Node_AVL curr_left= current.leftVideoNode;
						Video_T_Node_AVL curr_right= current.rightVideoNode;
						if(curr_left !=null)
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						printingLoop(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						printingLoop(current);
						}
				
		}
	}
	
	void printingLoop(Video_T_Node_AVL current)
	{
						Video_T_Node_AVL curr_left= current.leftVideoNode;
						Video_T_Node_AVL curr_right= current.rightVideoNode;
						if(curr_left !=null)
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						current=curr_left;
						if(current !=null)
							printingLoop(current);
						
						current=curr_right;
						if(current != null)
							printingLoop(current);
		
	}
	
	
	void deleteVideo(int keyVal)
	{
		Video_T_Node_AVL current=v_root;
		Video_T_Node_AVL parent=v_root;
		
		while(current !=null)
		{
					
				
				if(current.keyVal==keyVal)
				{
					
					if(current.checkin_status=='o')
					{
						System.out.println(" This video is rented to customer and not available in store to remove.");
						break;
					}
					
					
					Video_T_Node_AVL rightChild=current.rightVideoNode;
					Video_T_Node_AVL leftChild=current.leftVideoNode;
					
					
					Video_T_Node_AVL temp=current;
					//Video_T_Node_AVL rem_parent=current;
					
					if( (rightChild !=null && leftChild !=null) || (rightChild !=null & leftChild ==null))
					{
						
							while(rightChild.leftVideoNode != null)
							{
								//rem_parent=rightChild;
								temp=rightChild;
								rightChild=rightChild.leftVideoNode;
							}
							
							current.keyVal=rightChild.keyVal;
														
							
							if(rightChild.rightVideoNode != null)
							{
								if(temp.keyVal>rightChild.keyVal)
								temp.leftVideoNode=rightChild.rightVideoNode;
							
								else
								temp.rightVideoNode=rightChild.rightVideoNode;
							}
							
							else
							{
							
								if(temp.keyVal>rightChild.keyVal)
									temp.leftVideoNode=null;

								else								
									temp.rightVideoNode=null;
							}
							
					System.out.println("Video successfully removed from store !!!");
					break;
					}
					
					
					else if(rightChild ==null & leftChild !=null)
					{
						leftChild=current.leftVideoNode;
						temp=leftChild;
							while(leftChild.rightVideoNode !=null)
							{
								
								leftChild=leftChild.rightVideoNode;
							}
							
							current.keyVal=leftChild.keyVal;
							if(parent.keyVal<temp.keyVal)
								parent.rightVideoNode=current.leftVideoNode;
							else
								parent.leftVideoNode=current.leftVideoNode;
							
					System.out.println("Video successfully removed from store !!!");
					break;		
					}
					else if(rightChild ==null & leftChild ==null)
					{
						if(parent.keyVal > current.keyVal)
							parent.leftVideoNode=null;
						else
							parent.rightVideoNode=null;
						
					}
					System.out.println("Video successfully removed from store !!!");
					break;
					
				}
				else if(current.keyVal<keyVal){
				
					parent =current;
					current=current.rightVideoNode;
				
				}
				
				else
				{
					parent=current;
					current=current.leftVideoNode;			
				}
		}//while
		
		if(current==null)
		System.out.println("There is no video with the given details !!!");
	}
	
	
	
	
	
	void check_availablity(int keyVal,int args_length)
	{
		
		Video_T_Node_AVL current=v_root;
		Video_T_Node_AVL parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				if(current.checkin_status=='i')
				{
					if(args_length !=4)
					System.out.println("Video is available in the store.");
				}
				else
				{
					if(args_length !=4)
					System.out.println("Video is rented out to customer");	
				}
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		
		
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("Video with given detials is not registered in store.");
		}
		
		
	}
	
	
	
	void addCustomer(Customer_T_Node_AVL customerToAdd,int args_length)
	{
		
		
		if(c_root.keyCVal==-1)
		{
			c_root=customerToAdd;
			if(args_length !=4)
			System.out.println("Customer name :"+c_root.getName()+" ,Id : "+c_root.getId()+" ,KeyValue :"+c_root.getKeyCVal()+" is succesfully registered in the video store.");
			
		}
		else
		{
			
			
			Customer_T_Node_AVL current=c_root;
			Customer_T_Node_AVL parent=null;
			boolean left=false;
			boolean right=false;
			
			while(current !=null)
			{
			
					if(current.keyCVal>customerToAdd.keyCVal)
					{
						parent=current;
						current=current.leftCustomerNode;
						left=true;
						right=false;
							
					}
					else
					{
						parent=current;
						current=current.rightCustomerNode;
						right=true;
						left=false;
						
					}
			}
			current=customerToAdd;
			if(left==true){
				parent.leftCustomerNode=current;
				
			}
			else
			{
				parent.rightCustomerNode=current;
				
			}
			reArrangeCustomer(current);
			if(args_length !=4)
		System.out.println("Customer name :"+current.getName()+" ,Id : "+current.getId()+" ,KeyValue :"+current.getKeyCVal()+" is succesfully registered in the video store.");
		}
		
		
	}
	
	
	
void reArrangeCustomer(Customer_T_Node_AVL customerToAdd)
	{
		Customer_T_Node_AVL grandParent=c_root;
		Customer_T_Node_AVL parent=c_root;
		Customer_T_Node_AVL current=c_root;
		
		while(current !=null)
		{
			
			if(current.keyCVal==customerToAdd.keyCVal)
			{
				
				if(grandParent != parent)
				{
						if(grandParent.rightCustomerNode==parent && parent.rightCustomerNode==current && grandParent.leftCustomerNode==null)
						{
							Customer_T_Node_AVL tempgp=grandParent.getParentCustomerNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightCustomerNode.getKeyCVal()==grandParent.getKeyCVal())
									tempgp.setRightCustomerNode(parent);
								else
									tempgp.setLeftCustomerNode(parent);
							}
								
							if(grandParent.getKeyCVal() !=c_root.getKeyCVal())
							{
							grandParent.setParentCustomerNode(parent);
							grandParent.setRightCustomerNode(null);
							parent.setParentCustomerNode(tempgp);
							parent.setLeftCustomerNode(grandParent);
							break;
							}
							
							else
							{
								Customer_T_Node_AVL temp=c_root;
								parent.setLeftCustomerNode(c_root);
								c_root.setRightCustomerNode(null);
								c_root.setParentCustomerNode(parent);
								c_root=parent;
							}
						}
						
						else if(grandParent.leftCustomerNode==parent && parent.leftCustomerNode==current && grandParent.rightCustomerNode==null)
						{
							
							Customer_T_Node_AVL tempgp=grandParent.getParentCustomerNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightCustomerNode.getKeyCVal()==grandParent.getKeyCVal())
									tempgp.setRightCustomerNode(parent);
								else
									tempgp.setLeftCustomerNode(parent);
							}
							
							
							if(grandParent.getKeyCVal() !=c_root.getKeyCVal())
							{
							grandParent.setParentCustomerNode(parent);
							grandParent.setLeftCustomerNode(null);
							parent.setParentCustomerNode(tempgp);
							parent.setRightCustomerNode(grandParent);
							break;
							}
							
							else
							{
								Customer_T_Node_AVL temp=c_root;
								parent.setRightCustomerNode(c_root);
								c_root.setLeftCustomerNode(null);
								c_root.setParentCustomerNode(parent);
								c_root=parent;
								break;
							}
							
						}
						
						else if(grandParent.rightCustomerNode==parent && parent.leftCustomerNode==current && grandParent.leftCustomerNode==null)
						{
							
				
							Customer_T_Node_AVL tempgp=grandParent.getParentCustomerNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightCustomerNode.getKeyCVal()==grandParent.getKeyCVal())
									tempgp.setRightCustomerNode(current);
								else
									tempgp.setLeftCustomerNode(current);
							}
								
							if(grandParent.getKeyCVal() !=c_root.getKeyCVal())
							{
							grandParent.setParentCustomerNode(current);
							grandParent.setRightCustomerNode(null);
							parent.setParentCustomerNode(current);
							current.setRightCustomerNode(parent);
							current.setLeftCustomerNode(grandParent);
							parent.setLeftCustomerNode(null);
							current.setParentCustomerNode(tempgp);
							break;
							}
							
							else
							{
								Customer_T_Node_AVL temp=c_root;
								parent.setLeftCustomerNode(null);
								c_root.setRightCustomerNode(null);
								current.setLeftCustomerNode(grandParent);
								current.setRightCustomerNode(parent);
								current.setParentCustomerNode(null);
								grandParent.setParentCustomerNode(current);
								parent.setParentCustomerNode(current);
								c_root=current;
							}
							
						}
						
						else if(grandParent.leftCustomerNode==parent && parent.rightCustomerNode==current && grandParent.rightCustomerNode==null)
						{						
		
							
							Customer_T_Node_AVL tempgp=grandParent.getParentCustomerNode();
							
							if(tempgp != null)
							{
								if(tempgp.rightCustomerNode.getKeyCVal()==grandParent.getKeyCVal())
									tempgp.setRightCustomerNode(current);
								else
									tempgp.setLeftCustomerNode(current);
							}
								
							if(grandParent.getKeyCVal() !=c_root.getKeyCVal())
							{
							grandParent.setParentCustomerNode(current);
							grandParent.setLeftCustomerNode(null);
							parent.setParentCustomerNode(current);
							current.setRightCustomerNode(grandParent);
							current.setLeftCustomerNode(parent);
							parent.setRightCustomerNode(null);
							current.setParentCustomerNode(tempgp);
							break;
							}
							
							else
							{
								Customer_T_Node_AVL temp=c_root;
								parent.setRightCustomerNode(null);
								c_root.setLeftCustomerNode(null);
								current.setLeftCustomerNode(parent);
								current.setRightCustomerNode(grandParent);
								parent.setParentCustomerNode(current);
								grandParent.setParentCustomerNode(current);
								c_root=current;
							}
						
						}
							
				
				}
				break;
			}
			else if(current.keyCVal> customerToAdd.keyCVal)
			{
				grandParent=parent;
				parent=current;
				current=current.leftCustomerNode;
			}
			else if(current.keyCVal<customerToAdd.keyCVal)
			{
				grandParent=parent;
				parent=current;
				current=current.rightCustomerNode;
			}
		}
		
		
	}
	
	

	void printCustomer()
	{
		Customer_T_Node_AVL current=c_root;
		
		if(current==null)
		{
			System.out.println("There are No Customers registered in the video store !!!");
		}
		else
		{
						
				current=c_root;
				System.out.println("Customer names ,id & key values are :");
				System.out.println("Name : "+current.getName()+", Id : "+current.getId()+", Key value :"+current.getKeyCVal());
					
										
						Customer_T_Node_AVL curr_left= current.leftCustomerNode;
						Customer_T_Node_AVL curr_right= current.rightCustomerNode;
						if(curr_left !=null)
							System.out.println("Name : "+curr_left.getName()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyCVal());
						if(curr_right !=null)
							System.out.println("Name : "+curr_right.getName()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyCVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						printingLoop_Customer(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						printingLoop_Customer(current);
						}
				
		}
	}
	
	void printingLoop_Customer(Customer_T_Node_AVL current)
	{
						Customer_T_Node_AVL curr_left= current.leftCustomerNode;
						Customer_T_Node_AVL curr_right= current.rightCustomerNode;
						if(curr_left !=null)
							System.out.println("Name : "+curr_left.getName()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyCVal());
						if(curr_right !=null)
							System.out.println("Name : "+curr_right.getName()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyCVal());
						
						current=curr_left;
						if(current !=null)
							printingLoop_Customer(current);
						
						current=curr_right;
						if(current != null)
							printingLoop_Customer(current);
		
	}
	
	void deleteCustomer(int keyCVal)
	{
		Customer_T_Node_AVL current=c_root;
		Customer_T_Node_AVL parent=c_root;
		
		while(current !=null)
		{
					
				
				if(current.keyCVal==keyCVal)
				{
					if(current.rentedVideos.size() !=0)
					{
						System.out.println("Customer has rented some videos from the store and can not be delted from store until all videos are returned !!!");
						break;
					}						
					
					Customer_T_Node_AVL rightChild=current.rightCustomerNode;
					Customer_T_Node_AVL leftChild=current.leftCustomerNode;
					
					
					Customer_T_Node_AVL temp=current;
					
					if( (rightChild !=null && leftChild !=null) || (rightChild !=null & leftChild ==null))
					{
						
							while(rightChild.leftCustomerNode != null)
							{
								temp=rightChild;
								rightChild=rightChild.leftCustomerNode;
							}
							
							current.keyCVal=rightChild.keyCVal;
													
							
							if(rightChild.rightCustomerNode != null)
							{
								if(temp.keyCVal>rightChild.keyCVal)
								temp.leftCustomerNode=rightChild.rightCustomerNode;
							
								else
								temp.rightCustomerNode=rightChild.rightCustomerNode;
							}
							
							else
							{
							
								if(temp.keyCVal>rightChild.keyCVal)
									temp.leftCustomerNode=null;

								else								
									temp.rightCustomerNode=null;
							}
							
					System.out.println("Customer deleted succesfully from video store.");
					break;
					}
					
					
					else if(rightChild ==null & leftChild !=null)
					{
						leftChild=current.leftCustomerNode;
						temp=leftChild;
							while(leftChild.rightCustomerNode !=null)
							{
								
								leftChild=leftChild.rightCustomerNode;
							}
							
							current.keyCVal=leftChild.keyCVal;
							if(parent.keyCVal<temp.keyCVal)
								parent.rightCustomerNode=current.leftCustomerNode;
							else
								parent.leftCustomerNode=current.leftCustomerNode;
					
					System.out.println("Customer deleted succesfully from video store.");		
					break;		
					}
					else if(rightChild ==null & leftChild ==null)
					{
						if(parent.keyCVal > current.keyCVal)
							parent.leftCustomerNode=null;
						else
							parent.rightCustomerNode=null;
						
					}
					System.out.println("Customer deleted succesfully from video store.");
					break;
					
				}
				else if(current.keyCVal<keyCVal){
				
					parent =current;
					current=current.rightCustomerNode;
				
				}
				
				else
				{
					parent=current;
					current=current.leftCustomerNode;			
				}
		}//while
		
		if(current==null)
			System.out.println("There is no customer with the given details.");
	}
	
	
	boolean searchCustomer(int keyCVal)
	{
		boolean search=false;
		Customer_T_Node_AVL current=c_root;
		Customer_T_Node_AVL parent=c_root;
		
		while(current !=null)
		{
			
			if(current.keyCVal==keyCVal)
			{
				search=true;
				break;
			}
			else if(current.keyCVal> keyCVal)
			{
				parent=current;
				current=current.leftCustomerNode;
			}
			else if(current.keyCVal<keyCVal)
			{
				parent=current;
				current=current.rightCustomerNode;
			}
		}
		
		return search;
		
		
	}
	
	
	static void checkOutVideo(int keyVal,int keyCVal,int args_length)
	{
		Video_T_Node_AVL current=v_root;
		Video_T_Node_AVL parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				
				
				Customer_T_Node_AVL current_Cust=c_root;
				Customer_T_Node_AVL parent_Cust=c_root;
		
						while(current_Cust !=null)
						{
							
							if(current_Cust.keyCVal==keyCVal)
							{
								if(current.checkin_status=='i')
								{
									current.checkin_status='o';
									allRentedVideos.add(current.keyVal);
									current_Cust.rentedVideos.add(current.id);
									
									if(args_length !=4)
									System.out.println("The video id : "+current.id+" is successfully rented to customer with id : "+current_Cust.id);
								}
								else
								{
									if(args_length !=4)
									System.out.println("The video : "+current.id+" is already rented to other customer ");
								}
								break;
							
							}
							else if(current_Cust.keyCVal> keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.leftCustomerNode;
							}
							else if(current_Cust.keyCVal<keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.rightCustomerNode;
							}
						}
						
		
		
						if(current_Cust==null)
						{
							if(args_length !=4)
							System.out.println("There is no customer available with the given details !!!");
							break;
						}
				
				
				
				
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("There is no video available with the given details !!!");
							
		}
		
	}
	
	
	static void allRentedVideos()
	{
		if(allRentedVideos.size()>=1)
		{
			System.out.println("The KeyValues of rented videos are :");
			for(int i=0;i<allRentedVideos.size();i++)
			{
				System.out.println(allRentedVideos.get(i));
			}
		}
		else
			System.out.println("currently there are no rented videos !!! ");
		
		
		
	}

	
	
	
	
	
	static void checkInVideo(int keyVal,int keyCVal,int args_length)
	{
		Video_T_Node_AVL current=v_root;
		Video_T_Node_AVL parent=v_root;
		
		while(current !=null)
		{
			
			if(current.keyVal==keyVal)
			{
				
				
				Customer_T_Node_AVL current_Cust=c_root;
				Customer_T_Node_AVL parent_Cust=c_root;
		
						while(current_Cust !=null)
						{
							
							if(current_Cust.keyCVal==keyCVal)
							{
								if(current.checkin_status=='o')
								{
									current.checkin_status='i';
												
												for(int i=0;i<allRentedVideos.size();i++)
												{
													 if(keyVal==allRentedVideos.get(i))
													 {
														 allRentedVideos.remove(i);
														 break;
													 }
												}
												
												for(int i=0;i<current_Cust.rentedVideos.size();i++)
												{
													 if(current.id.equals(current_Cust.rentedVideos.get(i)))
													 {
														 current_Cust.rentedVideos.remove(i);
														 break;
													 }
												}
									
																				
									if(args_length !=4)
									System.out.println("The video id: "+current.id+" is successfully checked in from customer id : "+current_Cust.id);
								}
								else
								{
									if(args_length !=4)
									System.out.println("The video : "+current.id+" is already checked in to video store ");
								}
								break;
							
							}
							else if(current_Cust.keyCVal> keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.leftCustomerNode;
							}
							else if(current_Cust.keyCVal<keyCVal)
							{
								parent_Cust=current_Cust;
								current_Cust=current_Cust.rightCustomerNode;
							}
						}
						
		
		
						if(current_Cust==null)
						{
							if(args_length !=4)
							System.out.println("There is no customer available with the given details !!!");
							break;
						}
				
				
				
				
				break;
			}
			else if(current.keyVal> keyVal)
			{
				parent=current;
				current=current.leftVideoNode;
			}
			else if(current.keyVal<keyVal)
			{
				parent=current;
				current=current.rightVideoNode;
			}
		}
		if(current==null)
		{
			if(args_length !=4)
			System.out.println("There is no video available with the given details !!!");
							
		}
		
	}
	
	
	
	
	
	void inStoreVideos()
	{
		Video_T_Node_AVL current=v_root;
		
		if(current==null)
		{
			System.out.println("There are No videos stored in the video store !!!");
		}
		else
		{
					
				current=v_root;
				if(current.checkin_status=='i')
				{
				System.out.println("Video title ,id & key values are :");
				System.out.println("Title : "+current.getTitle()+", Id : "+current.getId()+", Key value :"+current.getKeyVal());
				}
				
				
				
					
										
						Video_T_Node_AVL curr_left= current.leftVideoNode;
						Video_T_Node_AVL curr_right= current.rightVideoNode;
						if(curr_left !=null)
							if(curr_left.checkin_status=='i')
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							if(curr_right.checkin_status=='i')
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						if(curr_left !=null)
						{
						current=curr_left;
						traverseLoop(current);
						}
						
						if(curr_right !=null)
						{
						current=curr_right;
						traverseLoop(current);
						}
				
		}
	}
	
	void traverseLoop(Video_T_Node_AVL current)
	{
						Video_T_Node_AVL curr_left= current.leftVideoNode;
						Video_T_Node_AVL curr_right= current.rightVideoNode;
						if(curr_left !=null)
							if(curr_left.checkin_status=='i')
							System.out.println("Title : "+curr_left.getTitle()+", Id : "+curr_left.getId()+", Key value :"+curr_left.getKeyVal());
						if(curr_right !=null)
							if(curr_right.checkin_status=='i')
							System.out.println("Title : "+curr_right.getTitle()+", Id : "+curr_right.getId()+", Key value :"+curr_right.getKeyVal());
						
						current=curr_left;
						if(current !=null)
							traverseLoop(current);
						
						current=curr_right;
						if(current != null)
							traverseLoop(current);
		
	}
	
	
	
	
	
	static void rentedByCustomer(int keyCVal)
	{
	

		Customer_T_Node_AVL current=c_root;
		Customer_T_Node_AVL parent=c_root;
		
		while(current !=null)
		{
			
			if(current.keyCVal==keyCVal)
			{
				if(current.rentedVideos.size()>=1)	
				{
					System.out.println("The videos rented by customer i : "+current.id+" is/ are :");
					for(int i=0;i<current.rentedVideos.size();i++)
					{
						System.out.println(current.rentedVideos.get(i));
					}
				}
				else
				System.out.println("There are no videos rented by this customer !!!");
				
				break;
			}
			else if(current.keyCVal> keyCVal)
			{
				parent=current;
				current=current.leftCustomerNode;
			}
			else if(current.keyCVal<keyCVal)
			{
				parent=current;
				current=current.rightCustomerNode;
			}
		}
		
		
		if(current==null)
			System.out.println("There is no customer with the given detials !!!");






	
	}
	
	
}



public class VideoStore
{		
		
	static int n=0;
	static void mainMenu()
	{
		System.out.println();
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("Select one of the following :");
		System.out.println("1: To add a video");
		System.out.println("2: To delete a video");
		System.out.println("3: To add a customer");
		System.out.println("4: To delete a customer");
		System.out.println("5: To check if a particular video is in store");
		System.out.println("6: To check out a video");
		System.out.println("7: To check in a video");
		System.out.println("8: To print all customers");
		System.out.println("9: To print all videos");
		System.out.println("10: To print in store videos");
		System.out.println("11: To print all rent videos");
		System.out.println("12: To print the videos rent by a customer");
		System.out.println("13: To exit");
		System.out.println("-------------------------------");
		
		System.out.println("Please enter your input :");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
	}
	
	static void startscreen(String ds)
	{
		
		while(n<=13)
			{
				
				mainMenu();	  
				if(n==1)
				{		
				
				System.out.println("Enter Video Title :");
				String title=sc1.next();
				
				System.out.println("Enter Video ID :");
				String id=sc1.next();

				Video v=new Video(title,id);
				
				
				if(ds.equals("SLL") || ds.equals("DLL"))
				System.out.println("*********RESULT IS :***********");
				
				if(ds.equals("SLL"))
				V_SLL.addVideo(v,1);
				
				else if(ds.equals("DLL"))				
					V_DLL.addVideo(v,1);
				
				else if(ds.equals("BST"))
				{
					System.out.println("Enter KeyValue of video :");
					int keyVal=sc1.nextInt();
					Video_T_Node v_bst=new Video_T_Node(title,id,keyVal);
					System.out.println("*********RESULT IS :***********");
					V_BST.addVideo(v_bst,1);
				}
				
				else if(ds.equals("AVL"))
				{
					System.out.println("Enter KeyValue of video :");
					int keyVal=sc1.nextInt();
					Video_T_Node_AVL v_avl=new Video_T_Node_AVL(title,id,keyVal);
					System.out.println("*********RESULT IS :***********");
					V_AVL.addVideo(v_avl,1);
				}
				
				
				}
				
				
				if(n==2)
				{		
				
				if(ds.equals("SLL") || ds.equals("DLL"))
				System.out.println("Enter Video Id to delete :");
				else if(ds.equals("BST") || ds.equals("AVL"))
				System.out.println("Enter Video key value to delete :");
				String id=sc1.next();
				
				System.out.println("*********RESULT IS :***********");	
				
				if(ds.equals("SLL"))
				V_SLL.deleteVideo(id);
				
				else if(ds.equals("DLL"))
					V_DLL.deleteVideo(id);
				
				else if(ds.equals("BST"))
					V_BST.deleteVideo(Integer.parseInt(id));
				
				else if(ds.equals("AVL"))
					V_AVL.deleteVideo(Integer.parseInt(id));
					
					
				}
				
				if(n==3)
				{
					System.out.println("Enter Customer name :");
					String name=sc1.next();
					
					System.out.println("Enter Customer Id :");
					String id=sc1.next();
					
									
					Customer c=new Customer(name,id);
					if(ds.equals("SLL") || ds.equals("DLL"))
						

					System.out.println("*********RESULT IS :***********");
					
					if(ds.equals("SLL"))
					V_SLL.addCustomer(c,1);
				
					else if(ds.equals("DLL"))
					V_DLL.addCustomer(c,1);
				
					else if(ds.equals("BST"))
					{
							System.out.println("Enter Customer key value :");
							int keyVal=sc1.nextInt();
							Customer_T_Node c_bst=new Customer_T_Node(name,id,keyVal);
							V_BST.addCustomer(c_bst,1);
					}
					
					else if(ds.equals("AVL"))
					{
							System.out.println("Enter Customer key value :");
							int keyVal=sc1.nextInt();
							Customer_T_Node_AVL c_avl=new Customer_T_Node_AVL(name,id,keyVal);
							V_AVL.addCustomer(c_avl,1);
					}
					
					
				}
				
				if(n==4)
				{
					if(ds.equals("SLL") || ds.equals("DLL"))
					System.out.println("Enter Customer Id to be deleted :");
					else if(ds.equals("BST")||ds.equals("AVL"))
					System.out.println("Enter customer key value to delete :");
					
					
					String id=sc1.next();
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.deleteCustomer(id);

					else if(ds.equals("DLL"))
					V_DLL.deleteCustomer(id);
					
					else if(ds.equals("BST"))
					V_BST.deleteCustomer(Integer.parseInt(id));
					
					else if(ds.equals("AVL"))
					V_AVL.deleteCustomer(Integer.parseInt(id));
				}
				
				if(n==5)
				{
					if(ds.equals("SLL") || ds.equals("DLL"))
					System.out.println("Enter the video Id to be checked :");
					else if(ds.equals("BST")||ds.equals("AVL"))
					System.out.println("Enter the video key value to be checked :");
					String id=sc1.next();
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.check_availablity(id,1);
				
					else if(ds.equals("DLL"))
					V_DLL.check_availablity(id,1);
					
					else if(ds.equals("BST"))
					V_BST.check_availablity(Integer.parseInt(id),1);
					
					else if(ds.equals("AVL"))
					V_AVL.check_availablity(Integer.parseInt(id),1);					
				}
				
				if(n==6)
				{
					System.out.println("Enter the video Id/key value to be checked out:");
					String vid=sc1.next();
					
					System.out.println("Enter the Customer Id / key value to whom video to be rented:");
					String cid=sc1.next();
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.rentVideo(vid,cid,1);
				
				
					else if(ds.equals("DLL"))
					V_DLL.rentVideo(vid,cid,1);
				
					else if(ds.equals("BST"))
					V_BST.checkOutVideo(Integer.parseInt(vid),Integer.parseInt(cid),1);
				
				
					else if(ds.equals("AVL"))
					V_AVL.checkOutVideo(Integer.parseInt(vid),Integer.parseInt(cid),1);
				}
				
				if(n==7)
				{
					System.out.println("Enter the video Id/ key value to be checked In:");
					String vid=sc1.next();
					
					System.out.println("Enter the customer Id/key value from whom video is to be checked In:");
					String cid=sc1.next();
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.mark_checkIn(vid,cid,1);
					
					else if(ds.equals("DLL"))
					V_DLL.mark_checkIn(vid,cid,1);
				
					else if(ds.equals("BST"))
					V_BST.checkInVideo(Integer.parseInt(vid),Integer.parseInt(cid),1);
				
					else if(ds.equals("AVL"))
					V_AVL.checkInVideo(Integer.parseInt(vid),Integer.parseInt(cid),1);
				}
				
				if(n==8)
				{
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.printCustomerBase();
				
					else if(ds.equals("DLL"))
					V_DLL.printCustomerBase();
				
					else if(ds.equals("BST"))
					V_BST.printCustomer();
				
					else if(ds.equals("AVL"))
					V_AVL.printCustomer();
				}
				
				if(n==9)
				{
				System.out.println("*********Here below is the list of all videos in the store :********");	
					if(ds.equals("SLL"))
					V_SLL.printAllVideo();
					
					else if(ds.equals("DLL"))
					V_DLL.printAllVideo();
				
					else if(ds.equals("BST"))
					V_BST.printVideo();
				
					else if(ds.equals("AVL"))
					V_AVL.printVideo();
				}
				
				if(n==10)
				{
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.inStoreVideos();
					
					else if(ds.equals("DLL"))
					V_DLL.inStoreVideos();
				
					else if(ds.equals("BST"))
					V_BST.inStoreVideos();
				
					else if(ds.equals("AVL"))
					V_AVL.inStoreVideos();
				}
				
				if(n==11)
				{
					System.out.println("*********RESULT IS :***********");
					if(ds.equals("SLL"))
					V_SLL.allRentedVideos();
					
					else if(ds.equals("DLL"))
					V_DLL.allRentedVideos();
				
					else if(ds.equals("BST"))
					V_BST.allRentedVideos();
				
					else if(ds.equals("AVL"))
					V_AVL.allRentedVideos();
				}
				
				if(n==12)
				{
					System.out.println("To view videos rented by a specific customer, Please enter customer Id:");
					String id=sc1.next();
					System.out.println("*********RESULT IS :***********");
					
					if(ds.equals("SLL"))
					V_SLL.rentedByCustomer(id);
					
					else if(ds.equals("DLL"))
					V_DLL.rentedByCustomer(id);
					
					else if(ds.equals("BST"))
					V_BST.rentedByCustomer(Integer.parseInt(id));
				
					else if(ds.equals("AVL"))
					V_AVL.rentedByCustomer(Integer.parseInt(id));
				}
				
				if(n==13)
				{
					System.out.println("Good Bye..");
					System.exit(1);
				}
		
		
			}
	
		
	}	
		

	static VideoStore_SLL V_SLL=new VideoStore_SLL();	
	static VideoStore_DLL V_DLL=new VideoStore_DLL();	
	static VideoStore_BST V_BST=new VideoStore_BST();	
	static VideoStore_AVL V_AVL=new VideoStore_AVL();	
	
	static	Scanner sc1=new Scanner(System.in);	
		
			
	public static void main(String []args){
		
		
	
		if(args.length==0)
		{
			System.out.println("Please pass valid command line argument while trying next time.");
			System.exit(1);
		}
		else if(args.length==1 && args[0].equals("SLL"))
		{
		
		startscreen("SLL");
			
		}
		
		else if(args.length==1 && args[0].equals("DLL"))
		{
		
		startscreen("DLL");
			
		}
		
		else if(args.length==1 && args[0].equals("BST"))
		{
		
		startscreen("BST");
			
		}
		
		else if(args.length==1 && args[0].equals("AVL"))
		{
		
		startscreen("AVL");
			
		}
		
		
		else if(args.length==4 )
		{
			int n1=Integer.parseInt(args[1]);
			int n2=Integer.parseInt(args[2]);
			int n3=Integer.parseInt(args[3]);
			
			
			ArrayList<Integer> random_keys_list=new ArrayList<Integer>();
			ArrayList<Integer> random_keys_cust_list=new ArrayList<Integer>();
			
			if(args[0].equals("BST") || args[0].equals("AVL"))
			{
			
					Random random_keys=new Random();
					int n=0;
					
					
					
					for(int i=0;i<n1;i++)
					{
						boolean loopRun=true;
						
							while(loopRun)
							{
								boolean duplicateKey=false;
								n=(int)(Math.random()*n1);
								
								
								for(int q=0;q<random_keys_list.size();q++)
								{
									if(n == random_keys_list.get(q))
									{
										duplicateKey=true;	
										
										break;		
									}
								}
								
								if(duplicateKey==false)
								{
									random_keys_list.add(n);
									loopRun=false;
									
								}
								
							}
						
						
					}
					
					
					Random random_keys_cust=new Random();
					int te=0;
					
					
					for(int i=0;i<n2;i++)
					{
						
						boolean loopRun=true;
						
							
							while(loopRun)
							{
								boolean duplicateKeyCust=false;
								te=(int)(Math.random()*n2);
								
								for(int q=0;q<random_keys_cust_list.size();q++)
								{
									if(te==random_keys_cust_list.get(q))
									{
										duplicateKeyCust=true;	
										break;		
									}
								}
								
								if(duplicateKeyCust==false)
								{
									random_keys_cust_list.add(te);
									loopRun=false;
									
								}
							}
								
								
					}
			
				/*for(int i=0;i<n1;i++)
				{
					Video_T_Node v_bst=new Video_T_Node(videoTitle,videoId,random_keys_list.get(i));
					V_BST.addVideo(v_bst);
				}*/
			
			}
			
			
			
			
			
			
			
			
			
			String videoTitle=null,videoId=null;
			ArrayList<String> videoIdList=new ArrayList<String>();
			
			for(int i=0;i<n1;i++)
			{
				videoTitle="video"+(i+1);
				videoId="v"+(i+1);
				
				Video newvideo=new Video(videoTitle,videoId);
				
				if(args[0].equals("SLL"))
				V_SLL.addVideo(newvideo,4);
			
				else if(args[0].equals("DLL"))
				V_DLL.addVideo(newvideo,4);
			
				else if(args[0].equals("BST"))
				{
					Video_T_Node newVideoBST=new Video_T_Node(videoTitle,videoId,random_keys_list.get(i));
					V_BST.addVideo(newVideoBST,4);
				}
				
				else if(args[0].equals("AVL"))
				{
					Video_T_Node_AVL newVideoAVL=new Video_T_Node_AVL(videoTitle,videoId,random_keys_list.get(i));
					V_AVL.addVideo(newVideoAVL,4);
				}
			
				videoIdList.add(videoId);
			}
			
			String custName=null,custId=null;
			ArrayList<String> custIdList=new ArrayList<String>();
						
			for(int i=0;i<n2;i++)
			{
				custName="customer"+(i+1);
				custId="cust"+(i+1);

				Customer newCustomer=new Customer(custName,custId);
				
				if(args[0].equals("SLL"))
				V_SLL.addCustomer(newCustomer,4);	

				else if(args[0].equals("DLL"))	
				V_DLL.addCustomer(newCustomer,4);
				
				else if(args[0].equals("BST"))	
				{
					Customer_T_Node cust_BST=new Customer_T_Node(custName,custId,random_keys_cust_list.get(i));
					V_BST.addCustomer(cust_BST,4);
				
				}
				
				else if(args[0].equals("AVL"))	
				{
					Customer_T_Node_AVL cust_AVL=new Customer_T_Node_AVL(custName,custId,random_keys_cust_list.get(i));
					V_AVL.addCustomer(cust_AVL,4);
				
				}
			
				custIdList.add(custId);
				
			}
			
			
					
			int tran=0;
			ArrayList<Integer> tranList=new ArrayList<Integer>();
			
			for(int i=0;i<n3;i++)
			{
				
				tran=5+(int)(Math.random()*3);
				tranList.add(tran);
			}
			
			
			Random r=new Random();
			long start=System.currentTimeMillis();
			
			for(int i=0;i<tranList.size();i++)
			{
				int temp=tranList.get(i);
				if(temp==5)
				{
					if(args[0].equals("SLL"))
					V_SLL.check_availablity(videoIdList.get(r.nextInt(videoIdList.size())),4);
				
					else if(args[0].equals("DLL"))	
					V_DLL.check_availablity(videoIdList.get(r.nextInt(videoIdList.size())),4);
				
					else if(args[0].equals("BST"))	
					V_BST.check_availablity(random_keys_list.get(r.nextInt(random_keys_list.size())),4);
					
					else if(args[0].equals("AVL"))	
					V_AVL.check_availablity(random_keys_list.get(r.nextInt(random_keys_list.size())),4);
				
				
				}
				else if (temp==6)
				{
					if(args[0].equals("SLL"))
					V_SLL.rentVideo(videoIdList.get(r.nextInt(videoIdList.size())),custIdList.get(r.nextInt(custIdList.size())),4);
				
					else if(args[0].equals("DLL"))
					V_DLL.rentVideo(videoIdList.get(r.nextInt(videoIdList.size())),custIdList.get(r.nextInt(custIdList.size())),4);
				
					else if(args[0].equals("BST"))
					V_BST.checkOutVideo(random_keys_list.get(r.nextInt(random_keys_list.size())),random_keys_cust_list.get(r.nextInt(random_keys_cust_list.size())),4);
				
					else if(args[0].equals("AVL"))
					V_AVL.checkOutVideo(random_keys_list.get(r.nextInt(random_keys_list.size())),random_keys_cust_list.get(r.nextInt(random_keys_cust_list.size())),4);
				}
				else if(temp==7)
				{
					if(args[0].equals("SLL"))
					V_SLL.mark_checkIn(videoIdList.get(r.nextInt(videoIdList.size())),custIdList.get(r.nextInt(custIdList.size())),4);
				
					else if(args[0].equals("DLL"))
					V_DLL.mark_checkIn(videoIdList.get(r.nextInt(videoIdList.size())),custIdList.get(r.nextInt(custIdList.size())),4);
				
					else if(args[0].equals("BST"))
					V_BST.checkInVideo(random_keys_list.get(r.nextInt(random_keys_list.size())),random_keys_cust_list.get(r.nextInt(random_keys_cust_list.size())),4);
				
					else if(args[0].equals("AVL"))
					V_AVL.checkInVideo(random_keys_list.get(r.nextInt(random_keys_list.size())),random_keys_cust_list.get(r.nextInt(random_keys_cust_list.size())),4);
				}
			}
			
			long end=System.currentTimeMillis();
			
			long timeConsumed=(end-start);
			
			System.out.println();
			System.out.println();
			System.out.println("***********End of Processing************");
			
			if(args[0].equals("SLL"))
			System.out.println("Time consumed in Single Linked List is (in milli seconds):"+timeConsumed);
			
			else if(args[0].equals("DLL"))
			System.out.println("Time consumed in Double Linked List is (in milli seconds):"+timeConsumed);
			
			else if(args[0].equals("BST"))
			System.out.println("Time consumed in BST is (in milli seconds):"+timeConsumed);
		
			else if(args[0].equals("AVL"))
			System.out.println("Time consumed in AVL tree is (in milli seconds):"+timeConsumed);
					
		}
			
	}
	
}