package view;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import domain.Rank;
import dto.LottoNumbersDto;
import dto.LottoResultDto;
import dto.LottoTicketDto;

public class OutputView {

	private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
	private static final String LOTTO_RESULT_HEADER_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final double SECOND_DECIMAL_PLACE = 100.0;

	public static void printLottoTicket(LottoTicketDto lottoTicketDto, int purchaseCount) {
		StringBuilder stringBuilder = new StringBuilder();
		appendLottoTicketHeader(stringBuilder, purchaseCount);
		appendLottoTicketBody(stringBuilder, lottoTicketDto);
		System.out.println(stringBuilder);
	}

	private static void appendLottoTicketHeader(StringBuilder stringBuilder, int purchaseCount) {
		stringBuilder.append(purchaseCount)
			.append(PURCHASE_MESSAGE)
			.append(System.lineSeparator());
	}

	private static void appendLottoTicketBody(StringBuilder stringBuilder, LottoTicketDto lottoTicketDto) {
		List<LottoNumbersDto> lottoNumbersDto = lottoTicketDto.getLottoNumbersDto();
		for (LottoNumbersDto numbersDto : lottoNumbersDto) {
			stringBuilder.append(numbersDto)
				.append(System.lineSeparator());
		}
	}

	public static void printLottoResult(LottoResultDto lottoResultDto, double profit) {
		StringBuilder stringBuilder = new StringBuilder();
		appendLottoResultHeader(stringBuilder);
		appendLottoResultBody(stringBuilder, lottoResultDto);
		appendProfitRate(stringBuilder, profit);
		System.out.println(stringBuilder);
	}

	private static void appendLottoResultHeader(StringBuilder stringBuilder) {
		stringBuilder.append(System.lineSeparator())
			.append(LOTTO_RESULT_HEADER_MESSAGE)
			.append(System.lineSeparator())
			.append(DIVIDING_LINE)
			.append(System.lineSeparator());
	}

	private static void appendLottoResultBody(StringBuilder stringBuilder, LottoResultDto lottoResultDto) {
		EnumMap<Rank, Integer> lottoResult = lottoResultDto.getLottoResult();
		for (Rank rank : Rank.getRanks()) {
			stringBuilder.append(rank.getCorrectNumber())
				.append("개 일치")
				.append(getBonus(rank));
			stringBuilder.append("(")
				.append(rank.getWinningAmount()).append("원) - ")
				.append(getCount(lottoResult, rank))
				.append("개")
				.append(System.lineSeparator());
		}
	}

	private static String getBonus(Rank rank) {
		if (rank.isBonus()) {
			return ", 보너스 볼 일치";
		}
		return " ";
	}

	private static int getCount(EnumMap<Rank, Integer> result, Rank rank) {
		return Optional.ofNullable(result.get(rank)).orElse(0);
	}

	private static void appendProfitRate(StringBuilder stringBuilder, double profitRate) {
		stringBuilder.append("총 수익률은 ")
			.append(Math.floor(profitRate * SECOND_DECIMAL_PLACE) / SECOND_DECIMAL_PLACE)
			.append("입니다.");
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}
