
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
	,('Turkish Lira'			,'TRY' ,'t') #pk=10
    
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
