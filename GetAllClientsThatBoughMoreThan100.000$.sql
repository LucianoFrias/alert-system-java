
SELECT id_cliente
FROM ventas
WHERE fecha >= DATEADD(MONTH, -12, GETDATE())
GROUP BY ventas.id_cliente
HAVING sum(importe) > 100000