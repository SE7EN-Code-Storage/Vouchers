package ca.tweetzy.vouchers.commands;

import ca.tweetzy.tweety.TabUtil;
import ca.tweetzy.vouchers.Vouchers;
import ca.tweetzy.vouchers.impl.Voucher;
import ca.tweetzy.vouchers.menu.MenuVoucherEdit;
import ca.tweetzy.vouchers.settings.Localization;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The current file has been created by Kiran Hart
 * Date Created: January 09 2022
 * Time Created: 6:46 p.m.
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise
 */
public final class CommandEdit extends AbstractVoucherCommand {

	public CommandEdit() {
		super("edit");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		final Player player = getPlayer();
		final String voucherId = args[0];

		final Voucher voucher = Vouchers.getVoucherManager().findVoucher(voucherId);
		if (voucher == null)
			returnTell(Localization.Error.VOUCHER_DOES_NOT_EXIST.replace("{voucher_id}", voucherId));

		new MenuVoucherEdit(voucher).displayTo(player);
	}

	@Override
	protected List<String> tabComplete() {
		if (args.length == 1)
			return TabUtil.complete(args[0], Vouchers.getVoucherManager().getVouchers().stream().map(Voucher::getId).collect(Collectors.toList()));
		return NO_COMPLETE;
	}
}
