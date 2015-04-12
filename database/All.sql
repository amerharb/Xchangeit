drop database CurEx;

create database CurEx;

use CurEx;

#this table will contain all the currencies that this Currency Exchange company will deal will
#not including the local currency
create table curr 
(  
	pk			    	int				  	null null
						auto_increment primary key
	,curr_name    		varchar(50)			not null
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
                                                 #1. I dont know how to create a calc column in mySQL and dont know if it is supported
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


use CurEx;

SET SQL_SAFE_UPDATES = 0;

insert into curr(curr_name ,ISO_symbol ,symbol) value 
	 ('British Pound'			,'GBP' ,'£') #pk=1
	,('US Dollar'				,'USD' ,'$') #pk=2
	,('Japanes Yan'				,'JPY' ,'¥') #pk=3
	,('Euro'					,'EUR' ,'€') #pk=4
	,('Syrian Pound'			,'SYP' ,'L') #pk=5
	,('Emirates Dirham'			,'AED' ,'D') #pk=6
	,('Libaness Pound'			,'LBP' ,'LL') #pk=7
	,('Australian Dollar'		,'AUD' ,'A$') #pk=8
	,('Canadian Dollar'			,'CAD' ,'C$') #pk=9
	,('Dupl. Canadian dollar'	,'CAA' ,'C') #pk=10 # wrong value to demostrate deleting it
    
;

#update currency rates at 2014-01-02 9:00 
insert into rates(curr ,rate_date ,rate ,sell_price ,buy_price) 
	values	 (1 ,'2014-01-02 9:00' ,11.627	,12.345	,10.989) 	#SEK rate 1 SEK = 0.08 GBP
			,(2 ,'2014-01-02 9:00' ,7.407	,7.692	,7.142)		#SEK rate 1 SEK = 0.1325 USD
			,(3 ,'2014-01-02 9:00' ,0.063	,0.066	,0.061)		#SEK rate 1 SEK = 15.67722 JPY
			,(4 ,'2014-01-02 9:00' ,9.523	,10		,9.09)		#SEK rate 1 SEK = 0.1065 EUR
			,(5 ,'2014-01-02 9:00' ,0.005	,0.008	,0.003)		#SEK rate 1 SEK = 200 SYP
			,(6 ,'2014-01-02 9:00' ,2		,1.9	,2.1)		#SEK rate 1 SEK = 0.5 AED
			,(7 ,'2014-01-02 9:00' ,0.005	,0.008	,0.003)		#SEK rate 1 SEK = 200 LBP
			,(8 ,'2014-01-02 9:00' ,6.6		,6.8	,7.4)		#SEK rate 1 SEK = 0.149 AUD 
			,(9 ,'2014-01-02 9:00' ,6.7		,6.9	,6.5)		#SEK rate 1 SEK = 0.147 CAD
			,(10 ,'2014-01-02 9:00' ,3.46	,3.6	,3.3)		#SEK rate 1 SEK = 0.287 TRY
;

#this is an example of how to get the last rate of curr = 1 
#will be usfull in the upcomeing insert command to fill the price and rate 
select rate ,sell_price ,buy_price from rates where curr = 1 
order by rate_date desc ,pk desc limit 1;


#Open cash reg. with 50000 SEK
insert into trans(trans_date ,trans_type ,cash) values ('2014-1-2 9:00' ,2 ,50000);    

#transfare currency from heaad office
insert into trans(trans_date ,curr ,curr_amt ,rate ,trans_type) 
	values	 ('2014-1-2 9:05' ,1 ,5000		,11.627	,1)
			,('2014-1-2 9:10' ,2 ,10000		,7.407	,1)         #transfate 10000 USD from head office
			,('2014-1-2 9:20' ,3 ,1000000	,0.063	,1)
			,('2014-1-2 9:30' ,4 ,5000		,9.523	,1)
;

#customers transactions buy and sell currencies
insert into trans(trans_date ,trans_type ,cash ,curr ,curr_amt ,sell_buy_price ,rate ,note) 
	values	 ('2014-1-2 9:44' 	,13	,769 	,2	,-100 	,7.692	,7.074 		,'selling 100 USD for 769') #thats mean the customer took 100 USD against 769 SEK
            ,('2014-1-2 9:55'	,13	,2000	,4	,-200	,10 	,9.523		,'selling 200 EUR for 2000')
			,('2014-1-2 10:11'	,3	,-630	,3	,10000	,0.061	,0.063	 	,'buy 10000 JPY for 630') #thats mean the customer take 630 SEK against 10000 JPY 
			,('2014-1-2 10:11'	,3	,-1648	,1	,150	,10.989	,11.627 	,'buy 150 GBP for 1648') #thats mean the customer take ? SEK against 150 GBP 
;

#update currency rates at 2014-01-02 11:00 
insert into rates(curr ,rate_date ,rate ,sell_price ,buy_price) 
	values	 (1 ,'2014-01-02 11:00' ,12		,13		,11) 		#GBP value goes up
			,(2 ,'2014-01-02 11:00' ,7		,7.5	,6.5)		#USD value down
			,(3 ,'2014-01-02 11:00' ,0.063	,0.066	,0.061)		#JPY stay the same 
			,(4 ,'2014-01-02 11:00' ,9.5	,10		,9)			#EUR go a little bit down but company decide to keep the value of sell and buy almost the same 
;

#customers transactions buy and sell currencies
insert into trans(trans_date ,trans_type ,cash ,curr ,curr_amt ,sell_buy_price ,rate ,note) 
	values	 ('2014-1-2 11:44' 	,13	,1500 	,2	,-200 	,7		,7.5 		,'selling 200 USD for 1500') #thats mean the customer took 200 USD against 1500 SEK new rates used
;

#stop dealing with Syrian, Emarates, and Lebaness Currency, so we flag the column inactive with true
#this way is better than delete the currency in case the currency has already and excisisting transactions/entries that is referenced to it from others tables
update curr set inactive = 1 where pk in (5, 6, 7);

#delete wrong value of currency, first delete the references rows, then from master table
delete from rates where curr = 10;
delete from curr where pk = 10; #delete the duplicated canadian dollar


#NOW THE FOLLOWING TRANS will be insert in diffreant style to demostrate proformed as if its live, so the date will not be inserted it will be filled from database server and the rate will be taken from the last one in rates table

#update currency rates with current time 
insert into rates(curr ,rate ,sell_price ,buy_price) 
	values	 (1 ,12.1	,13.1	,11.1) 		#GBP value goes little up again
			,(2 ,7.1	,7.6	,6.6)		#USD value goes little up 
			,(3 ,0.063	,0.066	,0.061)		#JPY stay the same 
			,(4 ,9.5	,10		,9)			#EUR stay the same
;


#customers transactions buy and sell currencies rates and date will be inserted by calc from other table and server clock 
insert into trans(trans_type ,cash ,curr ,curr_amt ,sell_buy_price ,rate ,note) 
	values	 (13	,1500 	,4	
    ,(select -(1500 / sell_price) from rates where curr = 4 order by rate_date desc ,pk desc limit 1)
    ,(select sell_price from rates where curr = 4 order by rate_date desc ,pk desc limit 1)
    ,(select rate from rates where curr = 4 order by rate_date desc ,pk desc limit 1)
    ,'selling 150 EUR for 1500 SEK') #thats mean the customer took 150 EUR against 1500 SEK 
;


#end of the day empty the shop from currencies and cash and transfare them to head office

#transfare cash
insert into trans(trans_type ,cash) 
	select 12, -sum(cash) from trans 
;

#transfare currency to heaad office
insert into trans(trans_type ,curr ,curr_amt ,rate) 
	select 11 trans_type 
    ,t.curr curr
    ,-sum(t.curr_amt) curr_amt
    ,(select r.rate from rates r where r.curr = t.curr order by r.rate_date desc ,r.pk desc limit 1) rate
    from trans t group by t.curr
;
#of course the above closing day transaction is not practical, because in real world we need to inqure first about the balances that we have 
#then count them, then make the transaction according to what acrually we transfare not what we are suppose to have


SET SQL_SAFE_UPDATES = 1;

#Reports
use CurEx;

#Last Currency rates and Prices
select c.ISO_symbol currency ,r.rate ,r.sell_price ,r.buy_price 
	from (select max(rate_date) rate_date ,curr from rates group by curr order by rate_date desc ,pk desc) as lr 
    left join rates r on r.rate_date = lr.rate_date and r.curr = lr.curr
    left join curr c on r.curr = c.pk 
;


#show current cash balance
select sum(cash) as Balance from trans;

#currency balances
select c.ISO_symbol Currency, c.symbol Symbol ,sum(curr_amt) as balance ,avg(t.rate) as "AVG rate" ,max(t.sell_buy_price) as "Max Price" ,min(t.sell_buy_price) as "Min Price"
	from curr c left join trans t on t.curr = c.pk
	group by c.pk
;

