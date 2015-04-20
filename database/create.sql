drop database CurEx;

create database CurEx;


use CurEx;


#this table will contain all the currencies that this Currency Exchange company will deal will

#not including the local currency

create table curr 
(  

	pk			    	int				  	not null
						auto_increment primary key
	,curr_name			varchar(50)			not null
	,ISO_symbol			char(3)				not null
						unique
	,symbol				nvarchar(10)		null
	,note             	nvarchar(100)		null
	,inactive			bit	 				null 
);

#this table will contain the exchange rate of the forgain currency to the local currency
create table rates
(
	pk					int					not null
						auto_increment Primary key
	,rate_date			datetime			not null
						default current_timestamp
	,curr				int					not null
						references curr(pk)
	,rate				float				not null # this represent the actual value of the currency, it will be used for internally
	,sell_price			float				not null # for sell transaction 
	,buy_price			float				not null # from buy transaction 
    ,note				nvarchar(100)		null # could be use full to register the info of rate source
);


#this table will containt all transaction transfater cash and currency and buy and sell
create table trans
(
	pk					int					not null
						auto_increment Primary key
	,trans_date			datetime			not null						
						default current_timestamp
    ,trans_type			smallint			not null # 1 for buy, 2 for sell
						check (trans_type in (1, 2, 3, 11, 12, 13))
						#transaction type
                        #1	transfare currency in
                        #2	transfare cash (local money) in
                        #3	selling currency for cash
                        #11	transfare currency out
                        #12	transfare cash out
                        #13	buying currency for cash
    ,cash				float				null #local currency
	,curr				int					null
						references curr(pk)
	,curr_amt			float				null
	,rate				float				null #its the actual value of currency in this transaction 
	,sell_buy_price		float				null #this column in fact it could be calculated column, basicly the value of it is the abslut result of 
												 #dividing cash on curr_amt: ABS(cash/curr_amt). but I will keep it normal column to 2 resones
                                                 #1. I dont know how to create a calc column in mySQL and dont know it is supported
                                                 #2. to document the value that we calc the cash and ignore the fraction of the result
	#this check will determind according to the transaction type which column should be null and which will be not null and the positive and negative value
    ,check(	(trans_type = 1 	and cash is null	and curr is not null	and curr_amt > 0 		and rate > 0		and sell_buy_price is null) or
			(trans_type = 2 	and cash > 0 		and curr is null 		and curr_amt is null 	and rate is null	and sell_buy_price is null) or
			(trans_type = 3 	and cash < 0 		and curr is not null 	and curr_amt > 0 		and rate > 0 		and sell_buy_price > 0) or
			(trans_type = 11 	and cash is null 	and curr is not null 	and curr_amt < 0 		and rate > 0 		and sell_buy_price is null) or
			(trans_type = 12 	and cash < 0 		and curr is null 		and curr_amt is null 	and rate is null 	and sell_buy_price is null) or
			(trans_type = 13 	and cash > 0 		and curr is not null 	and curr_amt < 0 		and rate > 0 		and sell_buy_price > 0)	)
	,note             	nvarchar(100)		null
);

