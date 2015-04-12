
#Reports
use CurEx;

#Last Currency rates and Prices
select c.ISO_symbol currency ,r.rate ,r.sell_price ,r.buy_price 
	from (select max(rate_date) rate_date ,curr from rates group by curr order by rate_date desc ,pk desc) as lr 
    left join rates r on r.rate_date = lr.rate_date and r.curr = lr.curr
    left join curr c on r.curr = c.pk 
;


#current cash balance
select sum(cash) as Balance from trans;

#currency balances
select c.ISO_symbol Currency, c.symbol Symbol ,sum(curr_amt) as balance ,avg(t.rate) as "AVG rate" ,max(t.sell_buy_price) as "Max Price" ,min(t.sell_buy_price) as "Min Price"
	from curr c left join trans t on t.curr = c.pk
	group by c.pk
;

