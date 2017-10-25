package com.wipro.book.service;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	public static void main(String[] args)
	{
		Administrator ad=new Administrator();
		AuthorDAO ab=new AuthorDAO();
		BookBean bb=new BookBean();
		bb.setIsbn("34552");
		bb.setBookName("SAMAYALrrre ");
		bb.setBookType('T');
		bb.setAuthor(ab.getAuthor(3));
		bb.setCost((float) 200.55);
		String result=ad.addBook(bb);
		System.out.println(result);
		bb=ad.viewBook("3455");
		System.out.println(bb.getBookName());
		System.out.println(bb.getBookType());
	}
	String addBook(BookBean bookBean)
	{
		String s="";
		if(bookBean==null || bookBean.getBookName().isEmpty() || bookBean.getIsbn().isEmpty() ||bookBean.getBookType()==' '
		||(bookBean.getBookType()!='G'&&bookBean.getBookType()!='T') || bookBean.getCost()==0
		|| bookBean.getAuthor().getAuthorName().isEmpty())
		{
			s="INVALID";
		}
		else
		{
			BookDAO bd=new BookDAO();
			int a=bd.createBook(bookBean);
			if(a==1)
			{
				s="SUCCESS";
			}
			if(a==0)
			{
				s="FAILURE";
			}
			
		}
		return s;	
	}
	BookBean viewBook(String isbn)
	{
		if(isbn.isEmpty())
		{
			return null;
		}
		else
		{
			BookDAO bd=new BookDAO();
			return bd.fetchBook(isbn);
		}
		
	}

}
