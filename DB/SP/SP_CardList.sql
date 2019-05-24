CREATE PROCEDURE SP_CardList
AS
BEGIN
	SELECT card_id, card_description
	FROM dbo.TBL_Cards
	order by card_id asc
END