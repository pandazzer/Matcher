# Matcher

Приложение читает из файла input.txt входные данные: n - число, далее n строк, m - число, далее m строк.
И создаёт файл output.txt с сопоставленными максимально похожими строками из первого множества со строками из второго
множества.

Пример:

input.txt:

4
гвоздь
шуруп
краска синяя
ведро для воды
3
краска
корыто для воды
шуруп 3х1.5

ouput.txt:

гвоздь:?
шуруп:шуруп 3х1.5
краска синяя:краска
ведро для воды:корыто для воды