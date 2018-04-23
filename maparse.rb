# coding: utf-8

def parse_optionals(optional_args)
  opts = {}

  optional_args.each do |arg|
    if arg.include? "="
      idx = arg.index("=")
      # assert idx >= 1
      k = arg[0 ... idx]
      v = arg[(idx + 1) .. -1]
      opts[k] = v
    else
      opts[arg] = true
    end
  end

  opts
end

def parse_args(args, names)
  opts = {}

  if ARGV.size == names.size
    # no optional arguments
  elsif ARGV.size > names.size
    opts = parse_optionals(ARGV[names.size .. -1])
  else
    raise "invalid arguments size"
  end

  names.each_with_index do |name, i|
    opts[name] = args[i]
  end

  opts
end
